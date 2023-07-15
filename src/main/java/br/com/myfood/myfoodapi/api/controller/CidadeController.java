package br.com.myfood.myfoodapi.api.controller;

import java.util.List;
import java.util.Map;

import br.com.myfood.myfoodapi.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
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
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Cidade atualiza(@PathVariable Long id, @RequestBody Cidade cozinha) {

        Cidade cidadeRecuperada = this.service.buscarPorId(id);

        BeanUtils.copyProperties(cozinha, cidadeRecuperada, "id");
        try {
            return this.service.salvar(cidadeRecuperada);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta(@PathVariable Long id) {
        this.service.remover(id);
    }

}
