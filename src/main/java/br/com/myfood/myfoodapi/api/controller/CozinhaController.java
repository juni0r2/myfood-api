package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import br.com.myfood.myfoodapi.api.assembler.CozinhaInputDisassembler;
import br.com.myfood.myfoodapi.api.assembler.CozinhaModelAssembler;
import br.com.myfood.myfoodapi.api.model.CozinhaModel;
import br.com.myfood.myfoodapi.api.model.input.CozinhaInput;
import br.com.myfood.myfoodapi.domain.exception.*;
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

import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.service.CadastroCozinhaService;

import javax.validation.Valid;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CadastroCozinhaService service;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @GetMapping
    public List<CozinhaModel> lista() {
        return this.cozinhaModelAssembler.toCollectionModel(this.service.listar());
    }

    @GetMapping("/{id}")
    public CozinhaModel busca(@PathVariable Long id) {
        return this.cozinhaModelAssembler.toModel(this.service.buscaPorId(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CozinhaModel adiciona(@RequestBody @Valid Cozinha cozinhaInput) {
        return this.cozinhaModelAssembler.toModel(this.service.salvar(cozinhaInput));
    }

    @PutMapping("/{id}")
    public CozinhaModel atualiza(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
        try {
            Cozinha cozinhaAtual = this.service.buscaPorId(id);
            this.cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
            return this.cozinhaModelAssembler.toModel(this.service.salvar(cozinhaAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta(@PathVariable Long id) {
        this.service.remover(id);
    }
}
