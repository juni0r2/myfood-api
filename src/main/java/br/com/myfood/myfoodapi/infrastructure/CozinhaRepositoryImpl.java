package br.com.myfood.myfoodapi.infrastructure;

import org.springframework.stereotype.Repository;

@Repository
public class CozinhaRepositoryImpl  {

    // @PersistenceContext
    // private EntityManager manager;

    // @Override
    // public List<Cozinha> listar() {
    //     return manager.createQuery("from Cozinha", Cozinha.class)
    //             .getResultList();
    // }

    // @Transactional
    // @Override
    // public Cozinha salvar(Cozinha cozinha) {
    //     return this.manager.merge(cozinha);
    // }

    // @Override
    // public Cozinha buscaPorId(Long id) {
    //     return this.manager.find(Cozinha.class, id);
    // }

    // @Transactional
    // @Override
    // public void remover(Cozinha cozinha) {
    //     cozinha = this.buscaPorId(cozinha.getId());
    //     this.manager.remove(cozinha);

    // }

}
