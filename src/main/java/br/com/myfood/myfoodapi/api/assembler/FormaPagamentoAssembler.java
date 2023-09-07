package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.FormaPagmentoModel;
import br.com.myfood.myfoodapi.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagmentoModel toModel(FormaPagamento formaPagamento) {
        return this.modelMapper.map(formaPagamento, FormaPagmentoModel.class);
    }

    public List<FormaPagmentoModel> toCollectionModel(Collection<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
