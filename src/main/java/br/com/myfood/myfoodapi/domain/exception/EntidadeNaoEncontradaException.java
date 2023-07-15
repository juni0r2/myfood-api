package br.com.myfood.myfoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) //reason = "Entidade não encontrada")
//public class EntidadeNaoEncontradaException extends ResponseStatusException {
//
//    public EntidadeNaoEncontradaException(HttpStatus status, String msg) {
//        super(status, msg);
//    }
//
//    public EntidadeNaoEncontradaException(String msg) {
//        this(HttpStatus.NOT_FOUND, msg);
//    }
//}
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
