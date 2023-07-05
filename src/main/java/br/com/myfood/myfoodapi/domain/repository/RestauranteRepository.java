package br.com.myfood.myfoodapi.domain.repository;

import br.com.myfood.myfoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

    List<Restaurante> consultaPorNome(String nome, Long cozinhaId);

    List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
}
