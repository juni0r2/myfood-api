package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Estado;
import br.com.myfood.myfoodapi.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

    public static final String MSG_ESTADO_ENCONTRADO = "Nenhum estado não encontrado com id: %d";
    @Autowired
    private EstadoRepository repository;

    public List<Estado> listar() {
        return this.repository.findAll();
    }

    public Estado buscaPorId(Long id) {
        return this.repository
        .findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_ESTADO_ENCONTRADO,id)));
    }

    public Estado salva(Estado estado){
        return this.repository.save(estado);
    }

    public void exclui(Long id) {
        try {
            Estado estado = this.buscaPorId(id);
            this.repository.delete(estado);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_ENCONTRADO, id));
        }
    }
}
