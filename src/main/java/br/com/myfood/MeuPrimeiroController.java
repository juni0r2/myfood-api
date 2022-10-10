package br.com.myfood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.myfood.modelo.Cliente;
import br.com.myfood.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {
	
	
	private AtivacaoClienteService service;
	
	public MeuPrimeiroController(AtivacaoClienteService service) {
		this.service = service;
		
		System.out.println("MeuPrimeiroController: "+service);
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {

		Cliente cliente = new Cliente("joao", "joao@xyz", "659878715418");
		service.ativar(cliente);
		
		return "OI";
	}
}
