package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.PedidoModelAssembler;
import br.com.myfood.myfoodapi.api.assembler.PedidoModelDisassembler;
import br.com.myfood.myfoodapi.api.assembler.PedidoResumoModelAssembler;
import br.com.myfood.myfoodapi.api.model.PedidoModel;
import br.com.myfood.myfoodapi.api.model.PedidoResumoModel;
import br.com.myfood.myfoodapi.api.model.input.PedidoInput;
import br.com.myfood.myfoodapi.domain.model.Pedido;
import br.com.myfood.myfoodapi.domain.model.Usuario;
import br.com.myfood.myfoodapi.domain.repository.PedidoRepository;
import br.com.myfood.myfoodapi.domain.repository.filter.PedidoFilter;
import br.com.myfood.myfoodapi.domain.service.CadastroPedidoService;
import br.com.myfood.myfoodapi.infrastructore.spec.PedidoSpecs;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
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

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
        Page<Pedido> pedidos = this.pedidoRepository.findAll(PedidoSpecs.usandoFiltro(pedidoFilter), pageable);
        List<PedidoResumoModel> pedidoResumoModels = this.pedidoResumoModelAssembler.toCollectionModel(pedidos.getContent());

        PageImpl<PedidoResumoModel> pagePedidos = new PageImpl<>(pedidoResumoModels, pageable, pedidos.getTotalElements());

        return pagePedidos;
    }

//    @GetMapping
//    public MappingJacksonValue lista(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = this.cadastroPedidoService.lista();
//        List<PedidoResumoModel> pedidoResumoModels = this.pedidoResumoModelAssembler.toCollectionModel(pedidos);
//
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(pedidoResumoModels);
//
//        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
//        simpleFilterProvider.setDefaultFilter(SimpleBeanPropertyFilter.serializeAll());
//        mappingJacksonValue.setFilters(simpleFilterProvider);
//
//        if (StringUtils.isNotBlank(campos)) {
//            simpleFilterProvider.setDefaultFilter(SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//
//        return mappingJacksonValue;
//    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscaPorId(@PathVariable String codigoPedido) {
        Pedido pedido = this.cadastroPedidoService.buscaPorCodigo(codigoPedido);
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
