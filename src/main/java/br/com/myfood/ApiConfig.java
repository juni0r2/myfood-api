package br.com.myfood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.myfood.notificacao.Notificador;
import br.com.myfood.service.AtivacaoClienteService;

@Configuration
public class ApiConfig {

	@Bean
	public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
		return new AtivacaoClienteService(notificador);
	}
}
