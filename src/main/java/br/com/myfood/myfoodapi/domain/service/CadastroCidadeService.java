package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Cidade;
import br.com.myfood.myfoodapi.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_NAO_ENCONTRADA = "Nenhuma cidade encontrada com id: %d";
    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";
    @Autowired
    private CidadeRepository repository;

    public List<Cidade> listar() {
        return this.repository.findAll();
    }

    public Cidade buscarPorId(Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
    }

    public Cidade salvar(Cidade cozinhaInput) {
        return this.repository.save(cozinhaInput);
    }

    public void remover(Long id) {

        try {
            Cidade cidadeRecuperada = this.buscarPorId(id);
            this.repository.delete(cidadeRecuperada);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA,id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
        }
    }
}
