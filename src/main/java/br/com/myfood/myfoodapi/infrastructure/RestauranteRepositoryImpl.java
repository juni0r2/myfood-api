package br.com.myfood.myfoodapi.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return this.manager.merge(restaurante);
    }

    @Override
    public List<Restaurante> listar() {
        return this.manager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    @Override
    public Restaurante buscarPorId(Long id) {
        return this.manager.find(Restaurante.class, id);
    }

    @Override
    public void remover(Restaurante restaurante) {
        restaurante = this.buscarPorId(restaurante.getId());
        this.manager.remove(restaurante);
    }

}
