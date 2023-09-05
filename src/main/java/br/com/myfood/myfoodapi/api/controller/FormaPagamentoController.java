package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.FormaPagamentoAssembler;
import br.com.myfood.myfoodapi.api.assembler.FormaPagamentoInputDisassembler;
import br.com.myfood.myfoodapi.api.model.FormaPagmentoModel;
import br.com.myfood.myfoodapi.api.model.input.FormaPagamentoInput;
import br.com.myfood.myfoodapi.domain.model.FormaPagamento;
import br.com.myfood.myfoodapi.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Autowired
    private FormaPagamentoAssembler formaPagamentoAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoDisassembler;

    @GetMapping
    public ResponseEntity<List<FormaPagmentoModel>> listar() {
        List<FormaPagamento> formaPagamentos = this.cadastroFormaPagamento.lista();
        return ResponseEntity.ok(this.formaPagamentoAssembler.toCollectionModel(formaPagamentos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagmentoModel> busca(@PathVariable Long id) {
        return ResponseEntity.ok(this.formaPagamentoAssembler.toModel(this.cadastroFormaPagamento.buscaPorId(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagmentoModel cria(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = this.formaPagamentoDisassembler.toDomainObject(formaPagamentoInput);
        return this.formaPagamentoAssembler.toModel(this.cadastroFormaPagamento.salva(formaPagamento));
    }

    @PutMapping("/{id}")
    public FormaPagmentoModel atualiza(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput input) {
        FormaPagamento formaPagamento = this.cadastroFormaPagamento.buscaPorId(id);
        this.formaPagamentoDisassembler.toCopyDomainObject(input, formaPagamento);
        return this.formaPagamentoAssembler.toModel(this.cadastroFormaPagamento.salva(formaPagamento));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        this.cadastroFormaPagamento.remove(id);
    }


}
