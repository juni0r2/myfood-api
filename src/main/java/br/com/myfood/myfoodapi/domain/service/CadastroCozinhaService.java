package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
    
    @Autowired
    private CozinhaRepository repository;

    public List<Cozinha> listar() {
        return this.repository.findAll();
    }

    public Cozinha salvar(Cozinha cozinha) {
        return this.repository.save(cozinha);
    }

    public Cozinha buscaPorId(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhuma cozinha com id: "+id));
    }

    public void remover(Cozinha cozinha) {
        cozinha = this.buscaPorId(cozinha.getId());
        this.repository.delete(cozinha);
    }
}
