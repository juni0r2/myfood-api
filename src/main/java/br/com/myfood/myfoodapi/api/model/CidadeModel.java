package br.com.myfood.myfoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeModel {

    private Long id;

    private String nome;

    @Valid
    @NotNull
    private EstadoModel estado;
}
