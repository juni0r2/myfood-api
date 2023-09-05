package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.GrupoNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.model.Grupo;
import br.com.myfood.myfoodapi.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroGrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo buscarPorId(Long id) {
        return this.grupoRepository.findById(id)
                .orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }

    public List<Grupo> lista() {
        return this.grupoRepository.findAll();
    }

    public Grupo salva(Grupo grupo) {
        return this.grupoRepository.save(grupo);
    }

    public void remove(Long id) {
        Grupo grupo = this.buscarPorId(id);
        this.grupoRepository.delete(grupo);
    }
}
