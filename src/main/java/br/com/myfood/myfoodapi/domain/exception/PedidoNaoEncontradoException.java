package br.com.myfood.myfoodapi.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public PedidoNaoEncontradoException(final String codigo) {
        super(String.format("Nenhum Pedido encontrado com codigo: %s", codigo));
    }
}
