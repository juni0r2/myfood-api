package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Estado;
import br.com.myfood.myfoodapi.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

    public static final String MSG_ESTADO_ENCONTRADO = "Nenhum estado não encontrado com id: %d";
    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";
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
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO,id));
        }
    }
}
