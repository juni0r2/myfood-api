package br.com.myfood.myfoodapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioInput {

    @NotBlank
    private String nome;

    @Email
    @NotNull
    private String email;


}
