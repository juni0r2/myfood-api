package br.com.myfood.myfoodapi.domain.repository;

import br.com.myfood.myfoodapi.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
