package br.com.myfood.myfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myfood.myfoodapi.domain.model.Permissao;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {

}
