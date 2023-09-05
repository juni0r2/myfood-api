package br.com.myfood.myfoodapi.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import br.com.myfood.myfoodapi.api.assembler.RestauranteInputDisassembler;
import br.com.myfood.myfoodapi.api.assembler.RestauranteModelAssembler;
import br.com.myfood.myfoodapi.api.model.RestauranteModel;
import br.com.myfood.myfoodapi.api.model.input.RestauranteInput;
import br.com.myfood.myfoodapi.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.service.CadastroRestauranteService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<RestauranteModel> lista() {
        return this.restauranteModelAssembler.toCollectionModel(this.cadastroRestaurante.listar());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel salva(@RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restaurante = this.restauranteInputDisassembler.toDomainObject(restauranteInput);
        return this.restauranteModelAssembler.toModel(this.cadastroRestaurante.salvar(restaurante));
    }

    @GetMapping("/{id}")
    public RestauranteModel buscaPorId(@PathVariable Long id) {
        Restaurante restaurante = this.cadastroRestaurante.buscaPorId(id);

        return this.restauranteModelAssembler.toModel(restaurante);
    }

    @PutMapping("/{id}")
    public RestauranteModel atualiza(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {

        try {
            Restaurante restauranteRecuperado = this.cadastroRestaurante.buscaPorId(id);
            this.restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteRecuperado);
            return this.restauranteModelAssembler.toModel(this.cadastroRestaurante.salvar(restauranteRecuperado));
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
    public RestauranteModel atualizaParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos,
                                       HttpServletRequest request) {

        Restaurante restauranteAtual = this.cadastroRestaurante.buscaPorId(id);

        merge(campos, restauranteAtual, request);

        return atualiza(id, null);
    }

    @PutMapping("{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id) {
        this.cadastroRestaurante.ativar(id);
    }

    @DeleteMapping("{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long id) {
        this.cadastroRestaurante.inativar(id);
    }


    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((campo, valor) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, campo);
                assert field != null;
                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }

}
