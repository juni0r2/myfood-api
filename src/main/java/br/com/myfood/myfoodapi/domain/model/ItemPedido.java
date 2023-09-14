package br.com.myfood.myfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    public void calcularPrecoTotal() {

        if (this.precoUnitario == null) {
            this.precoUnitario = BigDecimal.ZERO;
        }

        if (this.quantidade == null) {
            this.quantidade = 0;
        }

        this.precoTotal = this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }
}
