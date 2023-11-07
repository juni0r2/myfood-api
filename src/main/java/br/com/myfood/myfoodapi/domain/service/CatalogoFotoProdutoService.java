package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.model.FotoProduto;
import br.com.myfood.myfoodapi.domain.repository.ProdutoRespositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRespositoy produtoRespositoy;

    @Transactional
    public FotoProduto salvar(FotoProduto foto) {
        return produtoRespositoy.save(foto);
    }
}
