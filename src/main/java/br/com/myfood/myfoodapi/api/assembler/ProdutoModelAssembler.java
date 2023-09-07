package br.com.myfood.myfoodapi.api.assembler;

import br.com.myfood.myfoodapi.api.model.ProdutoModel;
import br.com.myfood.myfoodapi.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        return this.modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
