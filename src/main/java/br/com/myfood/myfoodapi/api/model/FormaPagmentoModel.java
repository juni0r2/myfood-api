package br.com.myfood.myfoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagmentoModel {

    private Long id;

    private String descricao;
}
