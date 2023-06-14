package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Estado>> listar() {
        List<Estado> estados = this.service.listar();
        return ResponseEntity
                .ok(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Estado estado = this.service.buscaPorId(id);
            return ResponseEntity.ok(estado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity
                    .notFound()
                    .build();

        }
    }

    @PostMapping
    public ResponseEntity<Estado> adiciona(@RequestBody Estado estadoInput) {
        this.service.salva(estadoInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody Estado estadoInput) {
        try {
            Estado estado = this.service.buscaPorId(id);
            BeanUtils.copyProperties(estadoInput, estado, "id");
            estado = this.service.salva(estado);
            return ResponseEntity.ok(estado);
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

}
