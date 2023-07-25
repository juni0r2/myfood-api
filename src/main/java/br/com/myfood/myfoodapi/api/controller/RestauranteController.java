package br.com.myfood.myfoodapi.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import br.com.myfood.myfoodapi.domain.exception.NegocioException;
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
    public List<Restaurante> lista() {
        return this.cadastroRestaurante.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante salva(@RequestBody Restaurante restauranteInput) {
        return this.cadastroRestaurante.salvar(restauranteInput);
    }

    @GetMapping("/{id}")
    public Restaurante buscaPorId(@PathVariable Long id) {
        return this.cadastroRestaurante.buscaPorId(id);
    }

    @PutMapping("/{id}")
    public Restaurante atualiza(@PathVariable Long id, @RequestBody Restaurante restauranteInput) {

        Restaurante restauranteRecuperado = this.cadastroRestaurante.buscaPorId(id);

        BeanUtils.copyProperties(restauranteInput, restauranteRecuperado,
                "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        try {
            return this.cadastroRestaurante.salvar(restauranteRecuperado);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        this.cadastroRestaurante.remover(id);
    }


    @PatchMapping("/{id}")
    public Restaurante atualizaParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {

        Restaurante restauranteAtual = this.cadastroRestaurante.buscaPorId(id);

        merge(campos, restauranteAtual);

        return atualiza(id, restauranteAtual);
    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((campo, valor) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, campo);
            field.setAccessible(true);

            System.out.println(campo + " = " + valor);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);

        });
    }

}
