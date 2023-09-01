package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import br.com.myfood.myfoodapi.api.assembler.CidadeInputDisassembler;
import br.com.myfood.myfoodapi.api.assembler.CidadeModelAssembler;
import br.com.myfood.myfoodapi.api.model.CidadeModel;
import br.com.myfood.myfoodapi.api.model.input.CidadeInput;
import br.com.myfood.myfoodapi.domain.exception.EstadoNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.myfood.myfoodapi.domain.model.Cidade;
import br.com.myfood.myfoodapi.domain.service.CadastroCidadeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService service;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public ResponseEntity<List<CidadeModel>> listar() {
        List<Cidade> lista = this.service.listar();
        return ResponseEntity.ok(this.cidadeModelAssembler.toCollectionModel(lista));
    }

    @GetMapping("/{id}")
    public CidadeModel buscaPorId(@PathVariable Long id) {
        return this.cidadeModelAssembler.toModel(this.service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adiciona(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = this.cidadeInputDisassembler.toDomainObject(cidadeInput);
            return this.cidadeModelAssembler.toModel(this.service.salvar(cidade));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public CidadeModel atualiza(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {

        try {
            Cidade cidadeRecuperada = this.service.buscarPorId(id);
            this.cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeRecuperada);
            return this.cidadeModelAssembler.toModel(this.service.salvar(cidadeRecuperada));
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
