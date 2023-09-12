package br.com.myfood.myfoodapi.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public PedidoNaoEncontradoException(Long pedidoId) {
        super(String.format("Nenhum Pedido encontrado com id: %d", pedidoId));
    }
}
