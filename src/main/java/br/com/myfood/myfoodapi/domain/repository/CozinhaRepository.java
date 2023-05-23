package br.com.myfood.myfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myfood.myfoodapi.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    
    // List<Cozinha> listar();
    // Cozinha buscaPorId(Long id);
    // Cozinha salvar(Cozinha cozinha);
    // void remover(Cozinha cozinha);
}
