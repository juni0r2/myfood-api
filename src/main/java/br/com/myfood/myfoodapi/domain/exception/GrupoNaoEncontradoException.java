package br.com.myfood.myfoodapi.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public GrupoNaoEncontradoException(Long id) {
        super(String.format("Nenhum grupo encontrado com id: %d", id));
    }
}
