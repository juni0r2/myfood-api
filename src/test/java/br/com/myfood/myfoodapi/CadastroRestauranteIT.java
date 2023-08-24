package br.com.myfood.myfoodapi;


import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.CozinhaRepository;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepository;
import br.com.myfood.myfoodapi.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

    @LocalServerPort
    private int port;

    @Autowired
    DatabaseCleaner databaseCleaner;

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    RestauranteRepository restauranteRepository;

    private int quantidadeRestaurantesCadastrados;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = this.port;
        RestAssured.basePath = "/myfood-api/restaurantes";

        this.databaseCleaner.clearTables();

        preparaDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarRestaurantes(){
        RestAssured.given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    private void preparaDados() {

        var cozinhaCaipira =  new Cozinha();
        cozinhaCaipira.setNome("Caipira");
        this.cozinhaRepository.save(cozinhaCaipira);

        var restaurante1 = new Restaurante();
        restaurante1.setNome("King Burguer");
        restaurante1.setTaxaFrete(new BigDecimal(10));
        restaurante1.setCozinha(cozinhaCaipira);
        this.restauranteRepository.save(restaurante1);

        this.quantidadeRestaurantesCadastrados = (int) this.restauranteRepository.count();
    }
}
