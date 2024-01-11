package br.com.myfood.myfoodapi.domain.exception;

public class FotoProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public FotoProdutoNaoEncontradoException(String msg) {
        super(msg);
    }

    public FotoProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        super(String.format("Nenhum arquivo com produto id %d encontrado para o restaurante de id: %d", produtoId, restauranteId));
    }
}
