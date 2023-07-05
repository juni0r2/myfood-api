package br.com.myfood.myfoodapi.infrastructore.repository;

import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepository;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepositoryQueries;
import br.com.myfood.myfoodapi.infrastructore.spec.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.myfood.myfoodapi.infrastructore.spec.RestauranteSpecs.*;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestauranteRepository repository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        String jpql = "from Restaurante where 1=1 and nome like :nome and taxaFrete between :taxaInicial and :taxaFinal ";

        return manager.createQuery(jpql, Restaurante.class)
                .setParameter("nome", "%"+nome+"%")
                .setParameter("taxaInicial", taxaInicial)
                .setParameter("taxaFinal", taxaFinal)
                .getResultList();
    }

    @Override
    public List<Restaurante> findCustomizado(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

        var jpql = new StringBuilder();

        Map<String, Object> parametros = new HashMap<>();

        jpql.append("from Restaurante where 1=1 ");

        if (StringUtils.hasLength(nome)) {
            jpql.append(" and nome like :nome ");
            parametros.put("nome","%"+nome+"%");
        }

        if (taxaInicial != null) {
            jpql.append(" and taxaFrete >= :taxaInicial");
            parametros.put("taxaInicial", taxaInicial);
        }

        if (taxaFinal != null) {
            jpql.append(" and taxaFrete <= :taxaFinal");
            parametros.put("taxaFinal", taxaFinal);
        }

        TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }

    @Override
    public List<Restaurante> findCriteira(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasLength(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
        }

        if (taxaFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = this.manager.createQuery(criteria);
        return query.getResultList();
    }

    public List<Restaurante> findComFreteGratisLazy(String nome) {
        return this.repository.findAll(comFreteGratis());
    }
}
