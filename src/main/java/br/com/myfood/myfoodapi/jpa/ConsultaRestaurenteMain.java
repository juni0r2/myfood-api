package br.com.myfood.myfoodapi.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.myfood.myfoodapi.MyfoodApiApplication;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.infrastructure.RestauranteRepositoryImpl;

public class ConsultaRestaurenteMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MyfoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

                RestauranteRepositoryImpl bean = applicationContext.getBean(RestauranteRepositoryImpl.class);

                Restaurante restaurante = new Restaurante();
                restaurante.setNome("BUGER KING");
                restaurante.setTaxaFrete(new BigDecimal("4.15"));

                bean.salvar(restaurante);

                bean.listar().forEach(r -> System.out.println(r));
    }
}
