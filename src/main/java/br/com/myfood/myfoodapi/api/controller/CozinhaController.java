package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CadastroCozinhaService service;

    @GetMapping
    public List<Cozinha> lista() {
        return this.service.listar();
    }

    @GetMapping("/{id}")
    public Cozinha busca(@PathVariable Long id) {
        return this.service.buscaPorId(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Cozinha adiciona(@RequestBody Cozinha cozinhaInput) {
        return this.service.salvar(cozinhaInput);
    }

    @PutMapping("/{id}")
    public Cozinha atualiza(@PathVariable Long id, @RequestBody Cozinha cozinha) {

        Cozinha cozinhaAtual = this.service.buscaPorId(id);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return this.service.salvar(cozinhaAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta(@PathVariable Long id) {
        this.service.remover(id);
    }
}
