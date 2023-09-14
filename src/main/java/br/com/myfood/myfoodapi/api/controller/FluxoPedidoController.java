package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxPedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirma(@PathVariable Long pedidoId) {
        this.fluxPedidoService.confirmar(pedidoId);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancela(@PathVariable Long pedidoId) {
        this.fluxPedidoService.cancelar(pedidoId);
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entrega(@PathVariable Long pedidoId) {
        this.fluxPedidoService.entregar(pedidoId);
    }
}
