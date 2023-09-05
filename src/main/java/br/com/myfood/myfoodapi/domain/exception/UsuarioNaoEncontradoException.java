package br.com.myfood.myfoodapi.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {
    public UsuarioNaoEncontradoException(Long id) {
        super(String.format("Nenhum usuário encontrado com id %d", id));
    }
}
