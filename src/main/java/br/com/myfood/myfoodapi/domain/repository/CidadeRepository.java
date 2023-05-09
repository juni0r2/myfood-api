package br.com.myfood.myfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myfood.myfoodapi.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    
}