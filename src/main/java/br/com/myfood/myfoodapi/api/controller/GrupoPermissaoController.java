package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.PermissaoModelAssembler;
import br.com.myfood.myfoodapi.api.model.PermissaoModel;
import br.com.myfood.myfoodapi.domain.model.Grupo;
import br.com.myfood.myfoodapi.domain.service.CadastroGrupoService;
import br.com.myfood.myfoodapi.domain.service.CadastroProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;
    @GetMapping
    public List<PermissaoModel> lista(@PathVariable Long grupoId) {
        Grupo grupo = this.cadastroGrupoService.buscarPorId(grupoId);
        return this.permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassocia(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        this.cadastroGrupoService.desassociarGrupoPermissao(grupoId, permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associa(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        this.cadastroGrupoService.associarGrupoPermissao(grupoId, permissaoId);
    }
}
