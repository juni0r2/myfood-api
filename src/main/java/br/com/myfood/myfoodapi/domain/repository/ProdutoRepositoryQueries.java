package br.com.myfood.myfoodapi.domain.repository;

import br.com.myfood.myfoodapi.domain.model.FotoProduto;
import br.com.myfood.myfoodapi.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto fotoProduto);
}
