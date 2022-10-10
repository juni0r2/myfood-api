package br.com.myfood.notificacao;

import org.springframework.stereotype.Component;

import br.com.myfood.modelo.Cliente;

@Component
public class NotificadorEmail {
	
	public void notificar(Cliente cliente, String msg) {
		System.out.printf("Notificando %s através do e-mail: %s: %s\n", cliente.getNome(), cliente.getEmail(), msg);
	}

}
