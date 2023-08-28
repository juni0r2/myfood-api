package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.exception.RestauranteNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroRestauranteService {

    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Nenhum restaurante encontrado com id: %d";
    public static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    public List<Restaurante> listar() {
        List<Restaurante> lista = this.restauranteRepository.findAll();
        lista.get(0).getCozinha().getNome();
        return lista;
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {

        Long idCozinha = restaurante.getCozinha().getId();
        Cozinha cozinha = this.cozinhaService.buscaPorId(idCozinha);
        restaurante.setCozinha(cozinha);

        return this.restauranteRepository.save(restaurante);
    }

    public Restaurante buscaPorId(Long id) {
        return this.restauranteRepository.findById(id)
            .orElseThrow(() -> new RestauranteNaoEncontradoException(
                    String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id)));
    }

    @Transactional
    public void remover(Long id) {

        try {
            Restaurante restaurante = this.buscaPorId(id);
            this.restauranteRepository.delete(restaurante);
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO,id));
        }
    }
}
