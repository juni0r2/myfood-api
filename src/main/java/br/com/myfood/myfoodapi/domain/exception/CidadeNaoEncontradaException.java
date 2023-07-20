package br.com.myfood.myfoodapi.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradaException(String msg) {
        super(msg);
    }
    public CidadeNaoEncontradaException(Long id) {
        this(String.format("Nenhum estado não encontrado com id: %d",id));
    }


}
