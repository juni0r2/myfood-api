package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.input.EstadoInput;
import br.com.myfood.myfoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class EstadoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Estado toDomainObject(EstadoInput estadoInput) {
        return this.modelMapper.map(estadoInput, Estado.class);
    }

    public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
        this.modelMapper.map(estadoInput, estado);
    }
}
