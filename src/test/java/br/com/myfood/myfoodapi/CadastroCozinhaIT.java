package br.com.myfood.myfoodapi;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.service.CadastroCozinhaService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.web.server.LocalServerPort;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	@Autowired
	CadastroCozinhaService cozinhaService;

	@LocalServerPort
	private int port;

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
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
				.basePath("/myfood-api/cozinhas")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConter4Cozinhas_QuandoConsultarCozinhas(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
				.basePath("/myfood-api/cozinhas")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.body("", Matchers.hasSize(4))
				.body("nome", Matchers.hasItems("Indiana", "Brasileira"));
	}

}
