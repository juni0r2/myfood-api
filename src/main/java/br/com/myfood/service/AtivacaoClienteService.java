package br.com.myfood.service;

import org.springframework.stereotype.Component;

import br.com.myfood.modelo.Cliente;
import br.com.myfood.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	private Notificador notificador;
	
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
		System.out.println("AtivacaoClienteService: " + notificador);
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
	}
}
