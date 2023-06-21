package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

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
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format("Nenhuma cozinha com id: %d", id)));
    }

    public void remover(Long idCozinha) {
        try {
            this.repository.deleteById(idCozinha);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", idCozinha));
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com código %d", idCozinha));
        }
    }
}
