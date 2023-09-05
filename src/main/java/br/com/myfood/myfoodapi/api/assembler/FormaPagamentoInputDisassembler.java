package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.input.FormaPagamentoInput;
import br.com.myfood.myfoodapi.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
        return this.modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }

    public void toCopyDomainObject(FormaPagamentoInput input, FormaPagamento formaPagamento) {
        this.modelMapper.map(input, formaPagamento);
    }
}
