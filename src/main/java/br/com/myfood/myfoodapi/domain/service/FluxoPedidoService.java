package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.NegocioException;
import br.com.myfood.myfoodapi.domain.model.Pedido;
import br.com.myfood.myfoodapi.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Transactional
    public void confirmar(final Long pedidoId) {
        Pedido pedido = this.cadastroPedidoService.buscaPorId(pedidoId);
        pedido.confirmar();
    }

    @Transactional
    public void cancelar(final Long pedidoId) {
        Pedido pedido = this.cadastroPedidoService.buscaPorId(pedidoId);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(final Long pedidoId) {
        Pedido pedido = this.cadastroPedidoService.buscaPorId(pedidoId);
        pedido.entregar();
    }
}
