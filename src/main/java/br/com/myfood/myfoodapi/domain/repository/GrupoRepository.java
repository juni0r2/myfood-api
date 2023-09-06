package br.com.myfood.myfoodapi.domain.repository;

import br.com.myfood.myfoodapi.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long> {
}
