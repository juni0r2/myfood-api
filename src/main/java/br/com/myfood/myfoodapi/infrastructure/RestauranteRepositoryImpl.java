package br.com.myfood.myfoodapi.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.myfood.myfoodapi.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        return this.manager.merge(restaurante);
    }

    public List<Restaurante> listar() {
        
        return this.manager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    public Restaurante buscarPorId(Long id) {
        return this.manager.find(Restaurante.class, id);
    }

    public void remover(Restaurante restaurante) {
        restaurante = this.buscarPorId(restaurante.getId());
        this.manager.remove(restaurante);
    }

}
