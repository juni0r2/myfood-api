package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.exception.EstadoNaoEncontradoException;
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
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return this.estadoRepository.findAll();
    }

    public Estado buscaPorId(Long id) {
        return this.estadoRepository
        .findById(id)
        .orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }

    public Estado salva(Estado estado){
        return this.estadoRepository.save(estado);
    }

    public void exclui(Long id) {
        try {
            Estado estado = this.buscaPorId(id);
            this.estadoRepository.delete(estado);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO,id));
        }
    }
}
