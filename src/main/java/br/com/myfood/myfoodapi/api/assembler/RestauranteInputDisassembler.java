package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.input.RestauranteInput;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toObjectModel(RestauranteInput restauranteInput) {
        return this.modelMapper.map(restauranteInput, Restaurante.class);
    }
}
