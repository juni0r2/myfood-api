package br.com.myfood.myfoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Cozinha buscar(@PathVariable Long id) {
        return this.service.buscaPorId(id);
    }
}
