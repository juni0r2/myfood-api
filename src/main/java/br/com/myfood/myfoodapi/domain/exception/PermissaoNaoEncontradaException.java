package br.com.myfood.myfoodapi.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
    public PermissaoNaoEncontradaException(Long id) {
        super(String.format("Nenhuma permissão encontrada com o id: %d",id));
    }
}
