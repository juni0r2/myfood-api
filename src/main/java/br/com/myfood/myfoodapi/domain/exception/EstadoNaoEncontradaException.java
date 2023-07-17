package br.com.myfood.myfoodapi.domain.exception;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradaException(String msg) {
        super(msg);
    }
    public EstadoNaoEncontradaException(Long id) {
        this(String.format("Nenhum estado não encontrado com id: %d",id));
    }


}
