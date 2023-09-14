package br.com.myfood.myfoodapi.domain.model;

import lombok.Getter;

@Getter
public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPedido(final String descricao) {
        this.descricao = descricao;
    }
}
