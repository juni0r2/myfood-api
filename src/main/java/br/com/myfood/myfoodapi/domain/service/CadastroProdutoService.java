package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.ProdutoNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.model.Produto;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.ProdutoRespositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRespositoy produtoRespositoy;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    public Produto buscaPorId(Long restauranteId, Long produtoId) {
        return this.produtoRespositoy.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }


    @Transactional
    public Produto salva(Produto produto) {
        return this.produtoRespositoy.save(produto);
    }
}
