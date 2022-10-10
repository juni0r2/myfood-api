package br.com.myfood.service;

import br.com.myfood.modelo.Cliente;
import br.com.myfood.notificacao.Notificador;
import br.com.myfood.notificacao.NotificadorSMS;

public class AtivacaoClienteService {

	private Notificador notificador;

	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
	}
}
