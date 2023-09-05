package br.com.myfood.myfoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    private Long id;
    private String nome;
    private String email;
}
