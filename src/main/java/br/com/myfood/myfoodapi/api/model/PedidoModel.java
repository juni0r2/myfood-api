package br.com.myfood.myfoodapi.api.model;

import br.com.myfood.myfoodapi.domain.model.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoModel {

    private Long id;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
    private FormaPagamentoModel formaPagamento;
    private EnderecoModel enderecoPedido;
    private String status;
    private List<ItemPedidoModel> itensPedidos;
}
