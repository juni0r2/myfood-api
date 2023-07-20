package br.com.myfood.myfoodapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradaException(String msg) {
        super(msg);
    }
    public CozinhaNaoEncontradaException(Long id) {
        this(String.format("Nenhum estado não encontrado com id: %d",id));
    }


}
