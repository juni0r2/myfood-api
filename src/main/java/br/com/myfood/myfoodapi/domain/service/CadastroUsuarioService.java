package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.NegocioException;
import br.com.myfood.myfoodapi.domain.exception.SenhaAtualNaoConfereException;
import br.com.myfood.myfoodapi.domain.exception.UsuarioNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.model.Usuario;
import br.com.myfood.myfoodapi.api.model.input.SenhaInput;
import br.com.myfood.myfoodapi.domain.model.dto.SenhaDTO;
import br.com.myfood.myfoodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroUsuarioService {

    private static final String MSG_SENHA_NAO_CONFERE = "Senha digitada não confere com senha atual";
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> lista() {
        return this.usuarioRepository.findAll();
    }

    public Usuario buscaPorId(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario salva(Usuario usuario) {
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

}