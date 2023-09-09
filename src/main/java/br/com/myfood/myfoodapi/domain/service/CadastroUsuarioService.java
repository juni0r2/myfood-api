package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.NegocioException;
import br.com.myfood.myfoodapi.domain.exception.SenhaAtualNaoConfereException;
import br.com.myfood.myfoodapi.domain.exception.UsuarioNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.model.Grupo;
import br.com.myfood.myfoodapi.domain.model.Usuario;
import br.com.myfood.myfoodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroUsuarioService {

    private static final String MSG_SENHA_NAO_CONFERE = "Senha digitada não confere com senha atual";
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    public List<Usuario> lista() {
        return this.usuarioRepository.findAll();
    }

    public Usuario buscaPorId(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario salva(Usuario usuario) {

        this.usuarioRepository.detach(usuario);
        
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findByEmail(usuario.getEmail());

        if (optionalUsuario.isPresent()) {
            throw new NegocioException(String.format("Já existe um usuário cadastrado com o email %s",
                    usuario.getEmail()));
        }

        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public void atualizaSenha(Long id, String senhaAtual, String novaSenha) {

        Usuario usuario = this.buscaPorId(id);
        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new SenhaAtualNaoConfereException(MSG_SENHA_NAO_CONFERE);
        }

        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void adicionaGrupoUsuario(Long usuarioId, Long grupoId) {
        Usuario usuario = this.buscaPorId(usuarioId);
        Grupo grupo = this.cadastroGrupoService.buscarPorId(grupoId);
        usuario.adiciona(grupo);
    }

    @Transactional
    public void removeGrupoUsuario(Long usuarioId, Long grupoId) {
        Usuario usuario = this.buscaPorId(usuarioId);
        Grupo grupo = this.cadastroGrupoService.buscarPorId(grupoId);
        usuario.remove(grupo);
    }
}
