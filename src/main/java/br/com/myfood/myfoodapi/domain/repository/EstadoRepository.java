package br.com.myfood.myfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myfood.myfoodapi.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    
}
