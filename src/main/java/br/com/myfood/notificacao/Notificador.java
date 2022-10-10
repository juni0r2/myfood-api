package br.com.myfood.notificacao;

import br.com.myfood.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String msg);

}