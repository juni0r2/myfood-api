package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.CozinhaModel;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaModel toModel(Cozinha cozinha) {
        return this.modelMapper.map(cozinha, CozinhaModel.class);
    }

    public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
