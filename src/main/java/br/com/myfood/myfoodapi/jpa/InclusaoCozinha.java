package br.com.myfood.myfoodapi.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import br.com.myfood.myfoodapi.MyfoodApiApplication;
import br.com.myfood.myfoodapi.domain.model.Cozinha;

@Component
public class InclusaoCozinha {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(MyfoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);
        
                CadastroCozinha bean = applicationContext.getBean(CadastroCozinha.class);

                Cozinha cozinha1 = new Cozinha();
                cozinha1.setId(1L);
                cozinha1.setNome("Brasileira");
                Cozinha cozinha2 = new Cozinha();
                cozinha2.setNome("Japonesa");

                cozinha1 = bean.salvar(cozinha1);
                cozinha2 = bean.salvar(cozinha2);

                System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
                System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());

                bean.listar().forEach(c -> System.out.println(c));
   }
}
