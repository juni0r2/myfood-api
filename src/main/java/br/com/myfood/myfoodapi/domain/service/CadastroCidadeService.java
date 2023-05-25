package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Cidade;
import br.com.myfood.myfoodapi.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository repository;

    public List<Cidade> listar() {
        return this.repository.findAll();
    }

    public Cidade buscarPorId(Long id) {
        return this.repository
            .findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhuma cidade encontrada com ID:"+id));
    }

    public Cidade salvar(Cidade cozinhaInput) {
        return this.repository.save(cozinhaInput);
    }

    public void remover(Cidade cidadeRecuperada) {
        this.repository.delete(cidadeRecuperada);
    }
}
