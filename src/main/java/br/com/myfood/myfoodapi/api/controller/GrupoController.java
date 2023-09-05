package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.GrupoInputDisassembler;
import br.com.myfood.myfoodapi.api.assembler.GrupoModelAssembler;
import br.com.myfood.myfoodapi.api.model.GrupoModel;
import br.com.myfood.myfoodapi.api.model.input.GrupoInput;
import br.com.myfood.myfoodapi.domain.model.Grupo;
import br.com.myfood.myfoodapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @GetMapping
    public List<GrupoModel> lista() {
        return this.grupoModelAssembler.toCollectionModel(this.cadastroGrupoService.lista());
    }

    @GetMapping("/{id}")
    public GrupoModel busca(@PathVariable Long id) {
        return this.grupoModelAssembler.toModel(this.cadastroGrupoService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel salva(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = this.grupoInputDisassembler.toDomainObject(grupoInput);
        return this.grupoModelAssembler.toModel(this.cadastroGrupoService.salva(grupo));
    }

    @PutMapping("/{id}")
    public GrupoModel atualiza(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = this.cadastroGrupoService.buscarPorId(id);
        this.grupoInputDisassembler.copyToDomainObject(grupoInput, grupo);
        return this.grupoModelAssembler.toModel(this.cadastroGrupoService.salva(grupo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        this.cadastroGrupoService.remove(id);
    }
}
