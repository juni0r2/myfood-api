package br.com.myfood.myfoodapi.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradoException(String msg) {
        super(msg);
    }
    public EstadoNaoEncontradoException(Long id) {
        this(String.format("Nenhum estado não encontrado com id: %d",id));
    }


}
