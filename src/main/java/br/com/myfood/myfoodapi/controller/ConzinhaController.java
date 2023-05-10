package br.com.myfood.myfoodapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.infrastructure.CozinhaRepositoryImpl;

@RestController
@RequestMapping("/cozinhas")
public class ConzinhaController {
    
    @Autowired
    private CozinhaRepositoryImpl service;

    @GetMapping
    public List<Cozinha> lista(){
        return this.service.listar();
    }
}
