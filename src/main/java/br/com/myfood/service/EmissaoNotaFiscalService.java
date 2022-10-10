package br.com.myfood.service;

import br.com.myfood.modelo.Cliente;
import br.com.myfood.modelo.Produto;
import br.com.myfood.notificacao.Notificador;
import br.com.myfood.notificacao.NotificadorEmail;

public class EmissaoNotaFiscalService {
	
	private Notificador notificador;

	public EmissaoNotaFiscalService(Notificador notificador) {
		this.notificador = notificador;
	}

	public void emitir(Cliente cliente, Produto produto) {
		// TODO emitir nota
		notificador.notificar(cliente, "Nota fiscal do produto: "+produto.getNome());
	}
}
