package br.com.myfood.myfoodapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

// @JsonRootName(value = "gastronomia") Caso queira mudar a representação 
@Data
@Entity
public class Cozinha {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonIgnore Caso queria ignorar o parammetro na representação
    // @JsonProperty("titulo") Caso queria mudar nome do parametro na representção
    @Column(name = "nom_cozinha")
    private String nome;
}
