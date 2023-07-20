package br.com.myfood.myfoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NegocioException extends RuntimeException {

    public NegocioException(String msg) {
        super(msg);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}
