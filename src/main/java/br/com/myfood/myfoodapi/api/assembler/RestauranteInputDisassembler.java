package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.input.RestauranteInput;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        return this.modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
        //Resolved [org.springframework.orm.jpa.JpaSystemException: identifier of an instance of
        // br.com.myfood.myfoodapi.domain.model.Cozinha was altered from 1 to 2;
        restaurante.setCozinha(new Cozinha());
        this.modelMapper.map(restauranteInput, restaurante);
    }
}
