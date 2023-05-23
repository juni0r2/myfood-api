package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    
    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        List<Restaurante> lista = this.cadastroRestaurante.listar();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restauranteInput) {
        Restaurante restauranteSalvo = this.cadastroRestaurante.salvar(restauranteInput);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restauranteSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscaPorId(@PathVariable Long id) {
        Restaurante restauranteRecuperado = this.cadastroRestaurante.buscaPorId(id);

        if (restauranteRecuperado == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(restauranteRecuperado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante restauranteInput) {
        Restaurante restauranteRecuperado = this.cadastroRestaurante.buscaPorId(id);

        if (restauranteRecuperado == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(restauranteInput, restauranteRecuperado, "id");
        restauranteRecuperado = this.cadastroRestaurante.salvar(restauranteRecuperado);
        return ResponseEntity.ok(restauranteRecuperado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurante> remover(@PathVariable Long id) {
        try {
            Restaurante restauranteRecuperado = this.cadastroRestaurante.buscaPorId(id);
            
            if (restauranteRecuperado == null) {
                return ResponseEntity.notFound().build();
            }
            
            this.cadastroRestaurante.remover(restauranteRecuperado);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
}
