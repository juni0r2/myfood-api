package br.com.myfood.myfoodapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import br.com.myfood.myfoodapi.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;


    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CidadeId.class)
    @NotNull
    @ManyToOne
    private Estado estado;
}
