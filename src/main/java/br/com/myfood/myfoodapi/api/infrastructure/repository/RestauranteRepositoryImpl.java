package br.com.myfood.myfoodapi.api.infrastructure.repository;

import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        String jpql = "from Restaurante where 1=1 and nome like :nome and taxaFrete between :taxaInicial and :taxaFinal ";

        return manager.createQuery(jpql, Restaurante.class)
                .setParameter("nome", "%"+nome+"%")
                .setParameter("taxaInicial", taxaInicial)
                .setParameter("taxaFinal", taxaFinal)
                .getResultList();
    }
}
