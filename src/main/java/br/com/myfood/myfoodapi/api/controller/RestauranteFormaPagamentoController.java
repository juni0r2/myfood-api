package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.FormaPagamentoAssembler;
import br.com.myfood.myfoodapi.api.model.FormaPagamentoModel;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    @Autowired
    private FormaPagamentoAssembler formaPagamentoAssembler;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public List<FormaPagamentoModel> lista(@PathVariable Long restauranteId) {
        Restaurante restaurante = this.cadastroRestauranteService.buscaPorId(restauranteId);
        return this.formaPagamentoAssembler.toCollectionModel(restaurante.getFormasPagamento());
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associaFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        this.cadastroRestauranteService.associa(restauranteId, formaPagamentoId);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociaFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        this.cadastroRestauranteService.desassocia(restauranteId, formaPagamentoId);
    }
}
