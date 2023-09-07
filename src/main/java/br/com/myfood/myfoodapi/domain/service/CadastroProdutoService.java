package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.ProdutoNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.model.Produto;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.ProdutoRespositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRespositoy produtoRespositoy;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    public Produto buscaProdutoPorRestaurante(Long restauranteId, Long produtoId) {
        return this.buscaPorId(produtoId,  restauranteId);
    }

    public Produto buscaPorId(Long idProduto, Long idRestaurante) {
        return this.produtoRespositoy.findByIdAndRestauranteId(idProduto, idRestaurante)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto, idRestaurante));
    }


    @Transactional
    public Produto salva(Produto produto) {
        return this.produtoRespositoy.save(produto);
    }
}
