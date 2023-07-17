package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.exception.EstadoNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Estado;
import br.com.myfood.myfoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroEstadoService {

    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";
    @Autowired
    private EstadoRepository repository;

    public List<Estado> listar() {
        return this.repository.findAll();
    }

    public Estado buscaPorId(Long id) {
        return this.repository
        .findById(id)
        .orElseThrow(() -> new EstadoNaoEncontradaException(id));
    }

    public Estado salva(Estado estado){
        return this.repository.save(estado);
    }

    public void exclui(Long id) {
        try {
            Estado estado = this.buscaPorId(id);
            this.repository.delete(estado);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradaException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO,id));
        }
    }
}
