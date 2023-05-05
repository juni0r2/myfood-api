package br.com.myfood.myfoodapi.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.myfood.myfoodapi.MyfoodApiApplication;
import br.com.myfood.myfoodapi.domain.model.Cozinha;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MyfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha bean = applicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha = bean.buscarPorId(1L);
        System.out.println(cozinha);
    }
}
