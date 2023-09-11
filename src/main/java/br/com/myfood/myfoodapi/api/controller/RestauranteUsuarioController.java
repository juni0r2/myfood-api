package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.UsuarioModelAssembler;
import br.com.myfood.myfoodapi.api.model.UsuarioModel;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/usuarios")
public class RestauranteUsuarioController {

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;
    @GetMapping
    public List<UsuarioModel> lista(@PathVariable Long restauranteId) {
        Restaurante restaurante = this.cadastroRestauranteService.buscaPorId(restauranteId);
        return this.usuarioModelAssembler.toCollectionModel(restaurante.getUsuarios());
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associaUsuarioRestaurante(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        this.cadastroRestauranteService.associaUsuarioRestaurante(restauranteId, usuarioId);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociaUsuarioRestaurante(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        this.cadastroRestauranteService.desassociaUsuarioRestaurante(restauranteId, usuarioId);
    }
}
