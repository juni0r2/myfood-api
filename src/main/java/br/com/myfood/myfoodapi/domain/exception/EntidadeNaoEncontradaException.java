package br.com.myfood.myfoodapi.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
    
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
