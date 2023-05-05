package br.com.myfood.myfoodapi.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return this.manager.merge(cozinha);
    }

    @Override
    public Cozinha buscaPorId(Long id) {
        return this.manager.find(Cozinha.class, id);
    }

    @Override
    public void remover(Cozinha cozinha) {
        cozinha = this.buscaPorId(cozinha.getId());
        this.manager.remove(cozinha);

    }

}
