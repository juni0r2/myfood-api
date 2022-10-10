package br.com.myfood.service;

import org.springframework.stereotype.Component;

import br.com.myfood.modelo.Cliente;
import br.com.myfood.notificacao.NotificadorEmail;

@Component
public class AtivacaoClienteService {

	private NotificadorEmail notificador;

	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
	}
}
