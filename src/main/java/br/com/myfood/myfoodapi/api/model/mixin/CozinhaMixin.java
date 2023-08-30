package br.com.myfood.myfoodapi.api.model.mixin;

import br.com.myfood.myfoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}
