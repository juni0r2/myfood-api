package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.UsuarioInputDisassembler;
import br.com.myfood.myfoodapi.api.assembler.UsuarioModelAssembler;
import br.com.myfood.myfoodapi.api.model.UsuarioModel;
import br.com.myfood.myfoodapi.api.model.input.SenhaInput;
import br.com.myfood.myfoodapi.api.model.input.UsuarioComSenhaInput;
import br.com.myfood.myfoodapi.api.model.input.UsuarioInput;
import br.com.myfood.myfoodapi.domain.model.Usuario;
import br.com.myfood.myfoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public List<UsuarioModel> lista() {
        return this.usuarioModelAssembler.toCollectionModel(this.cadastroUsuarioService.lista());
    }

    @GetMapping("/{id}")
    public UsuarioModel buscaPorId(@PathVariable Long id) {
        return this.usuarioModelAssembler.toModel(this.cadastroUsuarioService.buscaPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel salva(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
        Usuario usuario = this.usuarioInputDisassembler.toDomainObject(usuarioInput);
        return this.usuarioModelAssembler.toModel(this.cadastroUsuarioService.salva(usuario));
    }

    @PutMapping("/{id}")
    public UsuarioModel atualiza(@PathVariable Long id, @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuario = this.cadastroUsuarioService.buscaPorId(id);
        this.usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuario);
        return this.usuarioModelAssembler.toModel(this.cadastroUsuarioService.salva(usuario));
    }

    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizaSenha(@PathVariable Long id, @RequestBody @Valid SenhaInput senhaInput) {
        this.cadastroUsuarioService.atualizaSenha(id, senhaInput.getSenhaAtual(), senhaInput.getSenhaNova());
    }
}
