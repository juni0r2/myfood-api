package br.com.myfood.myfoodapi;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.repository.CozinhaRepository;
import br.com.myfood.myfoodapi.domain.service.CadastroCozinhaService;
import br.com.myfood.myfoodapi.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

	@Autowired
	CadastroCozinhaService cozinhaService;

	@Autowired
	DatabaseCleaner databaseCleaner;

	@Autowired
	CozinhaRepository cozinhaRepository;
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath="/myfood-api/cozinhas";

		this.databaseCleaner.clearTables();

		preparaDados();
	}

	@Test
	public void testarCadastroCozinhaComSucesso() {
		//cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		//ação
		novaCozinha = this.cozinhaService.salvar(novaCozinha);
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	public void testarCadastroCozinhaSemNome() {

		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cozinhaService.salvar(novaCozinha);
		});

		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		EntidadeEmUsoException cozinhaEmUso = Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
			this.cozinhaService.remover(1L);
		});

		assertThat(cozinhaEmUso).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		EntidadeNaoEncontradaException cozinhaNaoEncontrada = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			this.cozinhaService.remover(100L);
		});

		assertThat(cozinhaNaoEncontrada).isNotNull();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas(){
		RestAssured.given()
				.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConter4Cozinhas_QuandoConsultarCozinhas(){
		RestAssured.given()
				.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.body("", Matchers.hasSize(2))
				.body("nome", Matchers.hasItems("Indiana", "Brasileira"));
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
				.body("{ \"nome\" : \"Caipira\" }")
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	private void preparaDados() {
		var cozinha1 = new Cozinha();
		cozinha1.setNome("Indiana");
		this.cozinhaRepository.save(cozinha1);
		var cozinha2 = new Cozinha();
		cozinha2.setNome("Brasileira");
		this.cozinhaRepository.save(cozinha2);
	}

}

