package br.com.myfood.myfoodapi.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public RestauranteNaoEncontradoException(String msg) {
        super(msg);
    }
    public RestauranteNaoEncontradoException(Long id) {
        this(String.format("Nenhum estado encontrado com id: %d",id));
    }


}
