package br.com.myfood.myfoodapi.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.CozinhaRepository;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Nenhum restaurante encontrado com id: %d";
    public static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";
    
    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    public List<Restaurante> listar() {
        List<Restaurante> lista = this.repository.findAll();
        lista.get(0).getCozinha().getNome();
        return lista;
    }

    public Restaurante salvar(Restaurante restaurante) {

        Long idCozinha = restaurante.getCozinha().getId();
        Cozinha cozinha = this.cozinhaService.buscaPorId(idCozinha);
        restaurante.setCozinha(cozinha);

        return this.repository.save(restaurante);
    }

    public Restaurante buscaPorId(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id)));
    }

    public void remover(Long id) {

        try {
            Restaurante restaurante = this.buscaPorId(id);
            this.repository.delete(restaurante);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO,id));
        }
    }
}
