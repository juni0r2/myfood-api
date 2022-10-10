package br.com.myfood.modelo.di;

import br.com.myfood.modelo.Cliente;
import br.com.myfood.notificacao.Notificador;
import br.com.myfood.notificacao.NotificadorSMS;
import br.com.myfood.service.AtivacaoClienteService;

public class Main {
	
	public static void main(String[] args) {
		Cliente joao = new Cliente("Jão", "jao@xyz", "6598784285");
		Cliente maria = new Cliente("Maria", "maria@xyz", "6598784285");
		
		Notificador notificador = new NotificadorSMS();
		
		AtivacaoClienteService ativacaoClienteService = new AtivacaoClienteService(notificador);
		ativacaoClienteService.ativar(joao);
		ativacaoClienteService.ativar(maria);
	}
}
