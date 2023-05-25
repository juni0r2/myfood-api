package br.com.myfood.myfoodapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.CozinhaRepository;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
    
    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return this.repository.findAll();
    }

    public Restaurante salvar(Restaurante restaurante) {

        Long idCozinha = restaurante.getCozinha().getId();
        Optional<Cozinha> cozinha = this.cozinhaRepository.findById(idCozinha);

        if (!cozinha.isPresent()) {
            throw new EntidadeNaoEncontradaException("Nenhuma cozinha encontrada com id: "+ idCozinha);
        }

        return this.repository.save(restaurante);
    }

    public Restaurante buscaPorId(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum restaurante encontrado com id: "+ id));
    }

    public void remover(Restaurante restaurante) {
        restaurante = this.buscaPorId(restaurante.getId());
        this.repository.delete(restaurante);
    }
}
