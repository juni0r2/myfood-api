package br.com.myfood.myfoodapi.domain.repository;

import br.com.myfood.myfoodapi.domain.model.Produto;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRespositoy extends CustomJpaRepository<Produto, Long>{

    Optional<Produto> findByIdAndRestauranteId(Long idProduto, Long idRestaurante);
}
