package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Estado;
import br.com.myfood.myfoodapi.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadoService service;

    @GetMapping
    public List<Estado> listar() {
        return this.service.listar();
    }

    @GetMapping("/{id}")
    public Estado buscarPorId(@PathVariable Long id) {
        return this.service.buscaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adiciona(@RequestBody Estado estadoInput) {
        return this.service.salva(estadoInput);
    }

    @PutMapping("/{id}")
    public Estado atualiza(@PathVariable Long id, @RequestBody Estado estadoInput) {

        Estado estado = this.service.buscaPorId(id);

        BeanUtils.copyProperties(estadoInput, estado, "id");

        return this.service.salva(estado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta(@PathVariable Long id) {
        this.service.exclui(id);
    }

}
