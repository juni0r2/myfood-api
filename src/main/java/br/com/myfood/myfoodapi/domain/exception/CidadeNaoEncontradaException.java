package br.com.myfood.myfoodapi.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradaException(String msg) {
        super(msg);
    }

    public CidadeNaoEncontradaException(Long id) {
        this(String.format("Nenhuma cidade encontrada com id: %d", id));
    }


}
