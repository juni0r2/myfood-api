package br.com.myfood.myfoodapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.myfood.myfoodapi.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Estado {

    @NotNull(groups = Groups.CidadeId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
}
