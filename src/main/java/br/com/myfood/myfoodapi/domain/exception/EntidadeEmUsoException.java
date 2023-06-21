package br.com.myfood.myfoodapi.domain.exception;

public class EntidadeEmUsoException extends RuntimeException {
    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}
