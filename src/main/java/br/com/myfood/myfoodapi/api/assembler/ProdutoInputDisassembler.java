package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.input.ProdutoInput;
import br.com.myfood.myfoodapi.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    public Produto toDomainObject(ProdutoInput produtoInput) {
        return this.modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainOject(ProdutoInput produtoInput, Produto produto) {
        this.modelMapper.map(produtoInput, produto);
    }
}
