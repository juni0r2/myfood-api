package br.com.myfood.myfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myfood.myfoodapi.domain.model.Estado;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {
    
}
