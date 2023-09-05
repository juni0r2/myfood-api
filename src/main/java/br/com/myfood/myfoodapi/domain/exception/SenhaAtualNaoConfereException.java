package br.com.myfood.myfoodapi.domain.exception;

public class SenhaAtualNaoConfereException extends NegocioException {
    public SenhaAtualNaoConfereException(String msg) {
        super(msg);
    }
}
