package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.model.FotoProduto;
import br.com.myfood.myfoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(final FotoProduto foto) {

        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        Optional<FotoProduto> op = this.produtoRepository.findFotoById(restauranteId, produtoId);

        if (op.isPresent()) {
            this.produtoRepository.delete(op.get());
        }

        return this.produtoRepository.save(foto);
    }
}
