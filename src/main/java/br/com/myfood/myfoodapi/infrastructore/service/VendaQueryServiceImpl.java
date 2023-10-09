package br.com.myfood.myfoodapi.infrastructore.service;

import br.com.myfood.myfoodapi.domain.model.Pedido;
import br.com.myfood.myfoodapi.domain.model.StatusPedido;
import br.com.myfood.myfoodapi.domain.model.dto.VendaDiaria;
import br.com.myfood.myfoodapi.domain.service.VendaQueryService;
import br.com.myfood.myfoodapi.domain.service.filter.VendaDiariafilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    EntityManager manager;
    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariafilter filtro) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var predicates = new ArrayList<Predicate>();

        var functionConvertTzDataCriacao = builder.function("convert_tz", Date.class, root.get("dataCriacao"),
                builder.literal("+00:00"),
                builder.literal("-03:00"));

        var functionDateDataCriacao = builder.function("date", Date.class, functionConvertTzDataCriacao);

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        List<StatusPedido> statusPedidos = Arrays.asList(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE);
        predicates.add(root.get("status").in(statusPedidos));

        if (filtro.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
        }

        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
        }

        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
        }

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }
}
