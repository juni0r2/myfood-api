package br.com.myfood.myfoodapi.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        super(String.format("Nenhum produto com id %d encontrado para o restaurante de id: %d", produtoId, restauranteId));
    }
}
