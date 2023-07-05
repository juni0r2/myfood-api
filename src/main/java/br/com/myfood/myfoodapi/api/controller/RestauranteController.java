package br.com.myfood.myfoodapi.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public ResponseEntity<List<Restaurante>> lista() {
        List<Restaurante> lista = this.cadastroRestaurante.listar();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<?> salva(@RequestBody Restaurante restauranteInput) {

        try {

            Restaurante restauranteSalvo = this.cadastroRestaurante.salvar(restauranteInput);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(restauranteSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscaPorId(@PathVariable Long id) {

        try {
            Restaurante restauranteRecuperado = this.cadastroRestaurante.buscaPorId(id);
            return ResponseEntity.ok(restauranteRecuperado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody Restaurante restauranteInput) {

        Restaurante restauranteRecuperado = null;
        try {
            restauranteRecuperado = this.cadastroRestaurante.buscaPorId(id);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }

        try {

            BeanUtils.copyProperties(restauranteInput, restauranteRecuperado,
                    "id",
                    "formasPagamento",
                    "endereco");
            restauranteRecuperado = this.cadastroRestaurante.salvar(restauranteRecuperado);
            return ResponseEntity.ok(restauranteRecuperado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            Restaurante restauranteRecuperado = this.cadastroRestaurante.buscaPorId(id);

            if (restauranteRecuperado == null) {
                return ResponseEntity.notFound().build();
            }

            this.cadastroRestaurante.remover(restauranteRecuperado);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizaParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {

        Restaurante restauranteAtual = this.cadastroRestaurante.buscaPorId(id);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }
        merge(campos, restauranteAtual);
        return atualiza(id, restauranteAtual);
    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((campo, valor) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, campo);
            field.setAccessible(true);

            System.out.println(campo +" = "+valor);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);

        });
    }

}
