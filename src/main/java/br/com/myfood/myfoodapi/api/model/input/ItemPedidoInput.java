package br.com.myfood.myfoodapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ItemPedidoInput {

    @NotNull
    private Integer quantidade;

    private String observacao;

    @NotNull
    private Long produtoId;
}
