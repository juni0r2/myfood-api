package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.input.PedidoInput;
import br.com.myfood.myfoodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pedido toDomainObject(PedidoInput pedidoInput) {
        return this.modelMapper.map(pedidoInput, Pedido.class);
    }

    public void toCopyDomainObject(PedidoInput pedidoInput, Pedido pedido) {
        this.modelMapper.map(pedidoInput, pedido);
    }
}
