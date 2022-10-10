package br.com.myfood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.myfood.notificacao.Notificador;
import br.com.myfood.notificacao.NotificadorEmail;

@Configuration
public class NotificadorConfig {

	@Bean
	public Notificador notificadorEmail() {
		NotificadorEmail notificadorEmail = new NotificadorEmail("smpt.myfood.com");
		notificadorEmail.setCaixaAlta(true);
		return notificadorEmail;
	}
	
}
