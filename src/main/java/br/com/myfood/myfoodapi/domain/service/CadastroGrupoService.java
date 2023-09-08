package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.exception.GrupoNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.model.Grupo;
import br.com.myfood.myfoodapi.domain.model.Permissao;
import br.com.myfood.myfoodapi.domain.repository.GrupoRepository;
import br.com.myfood.myfoodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroGrupoService {
    private static final String MSG_GRUPO_EM_USO
            = "Grupo de código %d não pode ser removido, pois está em uso";

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissaoService;

    public Grupo buscarPorId(Long id) {
        return this.grupoRepository.findById(id)
                .orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }

    public List<Grupo> lista() {
        return this.grupoRepository.findAll();
    }

    @Transactional
    public Grupo salva(Grupo grupo) {
        return this.grupoRepository.save(grupo);
    }

    @Transactional
    public void remove(Long id) {
        try {
            this.grupoRepository.deleteById(id);
            this.grupoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(id);
        }
    }

    @Transactional
    public void desassociarGrupoPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = this.buscarPorId(grupoId);
        Permissao permissao = this.cadastroPermissaoService.buscaPorId(permissaoId);
        grupo.desassocia(permissao);
    }

    @Transactional
    public void associarGrupoPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = this.buscarPorId(grupoId);
        Permissao permissao = this.cadastroPermissaoService.buscaPorId(permissaoId);
        grupo.associa(permissao);
    }
}
