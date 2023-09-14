package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{codigo}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxPedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirma(@PathVariable String codigo) {
        this.fluxPedidoService.confirmar(codigo);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancela(@PathVariable String codigo) {
        this.fluxPedidoService.cancelar(codigo);
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entrega(@PathVariable String codigo) {
        this.fluxPedidoService.entregar(codigo);
    }
}
