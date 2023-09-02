package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import br.com.myfood.myfoodapi.api.assembler.EstadoInputDisassembler;
import br.com.myfood.myfoodapi.api.assembler.EstadoModelAssembler;
import br.com.myfood.myfoodapi.api.model.EstadoModel;
import br.com.myfood.myfoodapi.api.model.input.EstadoInput;
import br.com.myfood.myfoodapi.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Estado;
import br.com.myfood.myfoodapi.domain.service.CadastroEstadoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadoService service;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoModel> listar() {
        return this.estadoModelAssembler.toCollectionModel(this.service.listar());
    }

    @GetMapping("/{id}")
    public EstadoModel buscarPorId(@PathVariable Long id) {
        return this.estadoModelAssembler.toModel(this.service.buscaPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adiciona(@RequestBody @Valid Estado estadoInput) {
        return this.estadoModelAssembler.toModel(this.service.salva(estadoInput));
    }

    @PutMapping("/{id}")
    public EstadoModel atualiza(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
        try {
            Estado estado = this.service.buscaPorId(id);
            this.estadoInputDisassembler.copyToDomainObject(estadoInput, estado);
            return this.estadoModelAssembler.toModel(this.service.salva(estado));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta(@PathVariable Long id) {
        this.service.exclui(id);
    }

}
