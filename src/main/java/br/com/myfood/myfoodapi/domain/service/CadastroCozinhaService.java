package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

import br.com.myfood.myfoodapi.domain.exception.CozinhaNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.repository.CozinhaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {

    public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
    public static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Page<Cozinha> listar(Pageable pageable) {
        return this.cozinhaRepository.findAll(pageable);
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return this.cozinhaRepository.save(cozinha);
    }

    public Cozinha  buscaPorId(Long id) {
        return this.cozinhaRepository.findById(id)
            .orElseThrow(() -> new CozinhaNaoEncontradaException(
                    String.format(MSG_COZINHA_NAO_ENCONTRADA, id)));
    }

    @Transactional
    public void remover(Long idCozinha) {
        try {
            this.cozinhaRepository.deleteById(idCozinha);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, idCozinha));
        } catch (EmptyResultDataAccessException e){
            throw new CozinhaNaoEncontradaException(
                    String.format(MSG_COZINHA_NAO_ENCONTRADA, idCozinha));
        }
    }
}
