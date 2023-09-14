package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.PedidoModelAssembler;
import br.com.myfood.myfoodapi.api.assembler.PedidoModelDisassembler;
import br.com.myfood.myfoodapi.api.assembler.PedidoResumoModelAssembler;
import br.com.myfood.myfoodapi.api.model.PedidoModel;
import br.com.myfood.myfoodapi.api.model.PedidoResumoModel;
import br.com.myfood.myfoodapi.api.model.input.PedidoInput;
import br.com.myfood.myfoodapi.domain.model.Pedido;
import br.com.myfood.myfoodapi.domain.model.Usuario;
import br.com.myfood.myfoodapi.domain.service.CadastroPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoModelDisassembler pedidoModelDisassembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private CadastroPedidoService cadastroPedidoService;
    @GetMapping
    public List<PedidoResumoModel> lista() {
        return this.pedidoResumoModelAssembler.toCollectionModel(this.cadastroPedidoService.lista());
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscaPorId(@PathVariable Long pedidoId) {
        Pedido pedido = this.cadastroPedidoService.buscaPorId(pedidoId);
        return this.pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel salva(@RequestBody @Valid PedidoInput pedidoInput) {
        Pedido pedido = this.pedidoModelDisassembler.toDomainObject(pedidoInput);
        pedido.setCliente(new Usuario());
        pedido.getCliente().setId(1L);
        return this.pedidoModelAssembler.toModel(this.cadastroPedidoService.emite(pedido));
    }
}
