package br.com.myfood.myfoodapi.domain.repository;

import java.util.List;

import br.com.myfood.myfoodapi.domain.model.Cozinha;

public interface CozinhaRepository {
    
    List<Cozinha> listar();
    Cozinha buscaPorId(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Cozinha cozinha);
}
