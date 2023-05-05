package br.com.myfood.myfoodapi.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.myfood.myfoodapi.domain.model.Cozinha;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return this.manager.merge(cozinha);
    }

    public Cozinha buscarPorId(long id) {
        return (Cozinha) this.manager.createQuery("from Cozinha c where c.id =:id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
