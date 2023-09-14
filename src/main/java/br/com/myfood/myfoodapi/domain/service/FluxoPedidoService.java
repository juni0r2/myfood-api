package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Transactional
    public void confirmar(final String codigo) {
        Pedido pedido = this.cadastroPedidoService.buscaPorCodigo(codigo);
        pedido.confirmar();
    }

    @Transactional
    public void cancelar(final String codigo) {
        Pedido pedido = this.cadastroPedidoService.buscaPorCodigo(codigo);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(final String codigo) {
        Pedido pedido = this.cadastroPedidoService.buscaPorCodigo(codigo);
        pedido.entregar();
    }
}
