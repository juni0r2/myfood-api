package br.com.myfood.notificacao;

import br.com.myfood.modelo.Cliente;

public class NotificadorEmail implements Notificador {

	
	private boolean caixaAlta = false;
	private String servidorSMTP;
	
	public NotificadorEmail(String servidorSMTP) {
		System.out.println("NotificadorEmail");
		this.servidorSMTP = servidorSMTP;
	}
	
	@Override
	public void notificar(Cliente cliente, String msg) {

		if (this.caixaAlta) {
			msg = msg.toUpperCase();
		}
		
		System.out.printf("Notificando %s através do e-mail: %s: SMTP: %s %s\n", cliente.getNome(), cliente.getEmail(), servidorSMTP, msg);
	}

	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}

}
