package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import br.com.myfood.myfoodapi.domain.exception.EstadoNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.myfood.myfoodapi.domain.model.Cidade;
import br.com.myfood.myfoodapi.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService service;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        List<Cidade> lista = this.service.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public Cidade buscaPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adiciona(@RequestBody Cidade cozinhaInput) {
        try {
            return this.service.salvar(cozinhaInput);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public Cidade atualiza(@PathVariable Long id, @RequestBody Cidade cozinha) {

        try {
            Cidade cidadeRecuperada = this.service.buscarPorId(id);

            BeanUtils.copyProperties(cozinha, cidadeRecuperada, "id");
            return this.service.salvar(cidadeRecuperada);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta(@PathVariable Long id) {
        this.service.remover(id);
    }

}
