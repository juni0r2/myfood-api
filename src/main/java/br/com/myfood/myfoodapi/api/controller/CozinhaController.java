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
    public ResponseEntity<?> busca(@PathVariable Long id) {
        try {
            Cozinha cozinha = this.service.buscaPorId(id);
            return ResponseEntity.ok(cozinha);
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Cozinha adiciona(@RequestBody Cozinha cozinhaInput) {
        return this.service.salvar(cozinhaInput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody Cozinha cozinha) {

        try {
            Cozinha cozinhaAtual = this.service.buscaPorId(id);

            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            cozinhaAtual = this.service.salvar(cozinhaAtual);

            return ResponseEntity.ok().body(cozinhaAtual);
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

    //    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleta(@PathVariable Long id) {
//
//        try {
//            this.service.remover(id);
//            return ResponseEntity
//                    .noContent()
//                    .build();
//        } catch (EntidadeEmUsoException e) {
//            return ResponseEntity
//                    .status(HttpStatus.CONFLICT)
//                    .body(e.getMessage());
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(e.getMessage());
//        }
//    }
    @DeleteMapping("/{id}")
    public void deleta(@PathVariable Long id) {
        this.service.remover(id);
    }
}
