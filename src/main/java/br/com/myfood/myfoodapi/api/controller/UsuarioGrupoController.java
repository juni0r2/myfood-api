package br.com.myfood.myfoodapi.api.controller;


import br.com.myfood.myfoodapi.api.assembler.GrupoModelAssembler;
import br.com.myfood.myfoodapi.api.assembler.UsuarioModelAssembler;
import br.com.myfood.myfoodapi.api.model.GrupoModel;
import br.com.myfood.myfoodapi.domain.model.Grupo;
import br.com.myfood.myfoodapi.domain.model.Usuario;
import br.com.myfood.myfoodapi.domain.service.CadastroGrupoService;
import br.com.myfood.myfoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
