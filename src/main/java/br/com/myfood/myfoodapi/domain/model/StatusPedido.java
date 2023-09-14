package br.com.myfood.myfoodapi.domain.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private final String descricao;
    private final List<StatusPedido> statusAnteriores;

    StatusPedido(final String descricao, final StatusPedido... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public boolean naoPodeAlterarpara(final StatusPedido statusNovo) {
        return !statusNovo.statusAnteriores.contains(this);
    }
}
