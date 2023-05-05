package br.com.myfood.myfoodapi.domain.repository;

import java.util.List;

import br.com.myfood.myfoodapi.domain.model.Restaurante;

public interface RestauranteRepository {
    
    Restaurante salvar(Restaurante restaurante);
    List<Restaurante> listar();
    Restaurante buscarPorId(Long id);
    void remover(Restaurante restaurante);
}
