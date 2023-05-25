package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Estado;
import br.com.myfood.myfoodapi.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> listar() {
        return this.repository.findAll();
    }

    public Estado buscarPorId(Long id) {
        return this.repository
        .findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum estado não encontrado com id: "+id));
    }
    
}
