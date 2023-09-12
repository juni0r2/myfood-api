package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.PedidoModel;
import br.com.myfood.myfoodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModel toModel(Pedido pedido) {
        return this.modelMapper.map(pedido, PedidoModel.class);
    }

    public List<PedidoModel> toCollectionModel(Collection<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
