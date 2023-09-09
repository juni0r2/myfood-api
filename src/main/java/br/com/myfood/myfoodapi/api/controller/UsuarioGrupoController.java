package br.com.myfood.myfoodapi.api.controller;


import br.com.myfood.myfoodapi.api.assembler.GrupoModelAssembler;
import br.com.myfood.myfoodapi.api.model.GrupoModel;
import br.com.myfood.myfoodapi.domain.model.Usuario;
import br.com.myfood.myfoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private GrupoModelAssembler usuarioModelAssembler;

    @Autowired
    private CadastroUsuarioService  cadastroUsuarioService;

    @GetMapping
    public List<GrupoModel> lista(@PathVariable final Long usuarioId) {
        Usuario usuario = this.cadastroUsuarioService.buscaPorId(usuarioId);
        return this.usuarioModelAssembler.toCollectionModel(usuario.getGrupos());
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adiciona(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        this.cadastroUsuarioService.adicionaGrupoUsuario(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        this.cadastroUsuarioService.removeGrupoUsuario(usuarioId, grupoId);
    }
}
