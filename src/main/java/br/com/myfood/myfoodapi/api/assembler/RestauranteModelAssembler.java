package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.controller.RestauranteController;
import br.com.myfood.myfoodapi.api.model.CozinhaModel;
import br.com.myfood.myfoodapi.api.model.RestauranteModel;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModel toModel(Restaurante restaurante) {
        return this.modelMapper.map(restaurante, RestauranteModel.class);
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
