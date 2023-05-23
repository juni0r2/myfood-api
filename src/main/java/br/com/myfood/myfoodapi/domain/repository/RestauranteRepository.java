package br.com.myfood.myfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myfood.myfoodapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    
    // Restaurante salvar(Restaurante restaurante);
    // List<Restaurante> listar();
    // Restaurante buscarPorId(Long id);
    // void remover(Restaurante restaurante);
}
