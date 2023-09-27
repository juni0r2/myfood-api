package br.com.myfood.myfoodapi.infrastructore.spec;

import br.com.myfood.myfoodapi.domain.model.Pedido;
import br.com.myfood.myfoodapi.domain.repository.filter.PedidoFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, query, builder) -> {

            root.fetch("restaurante").fetch("cozinha");
            root.fetch("cliente");

            var predicates = new ArrayList<Predicate>();

            if (filtro.getClienteId() != null ) {
                predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }

            if (filtro.getRestauranteId() != null ) {
                predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }

            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}