package br.com.myfood.myfoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
    
    @Autowired
    private RestauranteRepository repository;

    public List<Restaurante> listar() {
        return this.repository.findAll();
    }

    public Restaurante salvar(Restaurante restaurante) {
        return this.repository.save(restaurante);
    }

    public Restaurante buscaPorId(Long id) {
        return this.repository.findById(id)
            .orElse(null);
    }

    public void remover(Restaurante restaurante) {
        restaurante = this.buscaPorId(restaurante.getId());
        this.repository.delete(restaurante);
    }
}
