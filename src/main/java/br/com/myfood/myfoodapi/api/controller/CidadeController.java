package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Cidade cidadeEncontrada = this.service.buscarPorId(id);
            return ResponseEntity.ok(cidadeEncontrada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cozinhaInput) {
        this.service.salvar(cozinhaInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cozinha) {

        try {
            Cidade cozinhaRecuperada = this.service.buscarPorId(id);
            System.out.println(cozinhaRecuperada);
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
    public ResponseEntity<?> deletar(@PathVariable Long id) {

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
