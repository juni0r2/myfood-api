package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.GrupoModel;
import br.com.myfood.myfoodapi.api.model.input.GrupoInput;
import br.com.myfood.myfoodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Grupo toDomainObject(GrupoInput grupoInput) {
        return this.modelMapper.map(grupoInput, Grupo.class);
    }

    public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
        this.modelMapper.map(grupoInput, grupo);
    }
}
