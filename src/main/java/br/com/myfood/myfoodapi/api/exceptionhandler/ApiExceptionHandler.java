package br.com.myfood.myfoodapi.api.exceptionhandler;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest webRequest) {
        return this.handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> trataNegocioException(NegocioException e, WebRequest webRequest) {
        return this.handleExceptionInternal(e, e.getMessage(),  new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> trataEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest webRequest) {

        return this.handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problema.builder()
                    .dataHora(LocalDateTime.now())
                    .mensagem(status.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .dataHora(LocalDateTime.now())
                    .mensagem((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
