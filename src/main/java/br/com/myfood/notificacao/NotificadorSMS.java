package br.com.myfood.notificacao;

import br.com.myfood.modelo.Cliente;

public class NotificadorSMS implements Notificador {

	@Override
	public void notificar(Cliente cliente, String msg) {
		System.out.printf("Notificando %s por SMS através do telefone: %s: %s\n", cliente.getNome(),
				cliente.getTelefone(), msg);
	}

}
