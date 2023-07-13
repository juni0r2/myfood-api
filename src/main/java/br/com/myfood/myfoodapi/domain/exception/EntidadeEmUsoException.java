package br.com.myfood.myfoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) //reason="Entidade em uso")
public class EntidadeEmUsoException extends RuntimeException {
    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}
