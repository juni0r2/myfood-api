package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.ProdutoInputDisassembler;
import br.com.myfood.myfoodapi.api.assembler.ProdutoModelAssembler;
import br.com.myfood.myfoodapi.api.model.ProdutoModel;
import br.com.myfood.myfoodapi.api.model.input.ProdutoInput;
import br.com.myfood.myfoodapi.domain.model.Produto;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.ProdutoRepository;
import br.com.myfood.myfoodapi.domain.service.CadastroProdutoService;
import br.com.myfood.myfoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<ProdutoModel> lista(@PathVariable Long restauranteId, @RequestParam(required = false) boolean inativo) {
        Restaurante restaurante = this.cadastroRestauranteService.buscaPorId(restauranteId);
        List<Produto> produtos = null;

        if (inativo) {
            produtos = this.produtoRepository.findTodosByRestaurante(restaurante);
        } else {
            produtos = this.produtoRepository.findAtivosByRestaurante(restaurante);
        }
        return this.produtoModelAssembler.toCollectionModel(produtos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscaProdutoRestaurante(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = this.cadastroProdutoService.buscaPorId(restauranteId, produtoId);
        return this.produtoModelAssembler.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adiciona(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = this.cadastroRestauranteService.buscaPorId(restauranteId);
        Produto produto = this.produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);
        return this.produtoModelAssembler.toModel(this.cadastroProdutoService.salva(produto));
    }

    @PutMapping("/{idProduto}")
    public ProdutoModel atualiza(@PathVariable Long idProduto, @PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = this.cadastroProdutoService.buscaPorId(idProduto, restauranteId);
        this.produtoInputDisassembler.copyToDomainOject(produtoInput, produto);
        return this.produtoModelAssembler.toModel(this.cadastroProdutoService.salva(produto));
    }
}
