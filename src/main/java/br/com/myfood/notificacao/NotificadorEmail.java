package br.com.myfood.notificacao;

import br.com.myfood.modelo.Cliente;

public class NotificadorEmail implements Notificador {

	@Override
	public void notificar(Cliente cliente, String msg) {
		System.out.printf("Notificando %s através do e-mail: %s: %s\n", cliente.getNome(), cliente.getEmail(), msg);
	}

}
