package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.myfood.myfoodapi.api.model.CozinhaXmlWrapper;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.infrastructure.CozinhaRepositoryImpl;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepositoryImpl service;

    @GetMapping
    public List<Cozinha> lista() {
        return this.service.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaXmlWrapper listar() {
        return new CozinhaXmlWrapper(this.service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = this.service.buscaPorId(id);

        // return ResponseEntity.status(HttpStatus.OK).body(cozinha);

        // HttpHeaders headers = new HttpHeaders();
        // headers.add(HttpHeaders.LOCATION, "http://api.algafood.local:8080/cozinhas");

        // return ResponseEntity
        // .status(HttpStatus.FOUND)
        // .headers(headers)
        // .build();

        if (cozinha == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cozinha);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha dto) {
        return this.service.salvar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {

        Cozinha cozinhaAtual = this.service.buscaPorId(id);

        if (cozinhaAtual == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        cozinhaAtual = this.service.salvar(cozinhaAtual);

        return ResponseEntity.ok().body(cozinhaAtual);
    }
}
