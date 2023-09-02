package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.CozinhaModel;
import br.com.myfood.myfoodapi.api.model.input.CozinhaInput;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainObject(CozinhaInput cozinhaModel) {
        return this.modelMapper.map(cozinhaModel, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInput cozinhaModel, Cozinha cozinha) {
        this.modelMapper.map(cozinhaModel, cozinha);
    }
}
