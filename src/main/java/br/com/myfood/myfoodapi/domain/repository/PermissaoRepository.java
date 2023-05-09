package br.com.myfood.myfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myfood.myfoodapi.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    
}
