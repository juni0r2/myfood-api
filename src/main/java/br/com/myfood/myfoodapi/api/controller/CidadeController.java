package br.com.myfood.myfoodapi.api.controller;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> buscaPorId(@PathVariable Long id) {
        try {
            Cidade cidadeEncontrada = this.service.buscarPorId(id);
            return ResponseEntity.ok(cidadeEncontrada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cidade> adiciona(@RequestBody Cidade cozinhaInput) {
        this.service.salvar(cozinhaInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody Cidade cozinha) {

        try {
            Cidade cozinhaRecuperada = this.service.buscarPorId(id);
            BeanUtils.copyProperties(cozinha, cozinhaRecuperada, "id");
            cozinhaRecuperada = this.service.salvar(cozinhaRecuperada);
            return ResponseEntity
                    .ok(cozinhaRecuperada);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {

        try {
            Cidade cidadeRecuperada = this.service.buscarPorId(id);
            this.service.remover(cidadeRecuperada);
            return ResponseEntity
                    .noContent()
                    .build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

}
