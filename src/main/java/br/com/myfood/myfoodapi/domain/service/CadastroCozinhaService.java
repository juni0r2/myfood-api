package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

import br.com.myfood.myfoodapi.domain.exception.CozinhaNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

    public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
    public static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return this.cozinhaRepository.findAll();
    }

    public Cozinha salvar(Cozinha cozinha) {
        return this.cozinhaRepository.save(cozinha);
    }

    public Cozinha  buscaPorId(Long id) {
        return this.cozinhaRepository.findById(id)
            .orElseThrow(() -> new CozinhaNaoEncontradaException(
                    String.format(MSG_COZINHA_NAO_ENCONTRADA, id)));
    }

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
