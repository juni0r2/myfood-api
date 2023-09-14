package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.PedidoNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.exception.RestauranteFormaPagamentoException;
import br.com.myfood.myfoodapi.domain.model.*;
import br.com.myfood.myfoodapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    public List<Pedido> lista() {
        return this.pedidoRepository.findAll();
    }

    public Pedido buscaPorCodigo(final String codigo) {
        return this.pedidoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
    }

    @Transactional
    public Pedido emite(final Pedido pedido) {

        this.validaPedido(pedido);
        this.validaItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return this.pedidoRepository.save(pedido);
    }

    private void validaItens(Pedido pedido) {
        pedido.getItensPedido().forEach(itemPedido -> {
            Produto produto = this.cadastroProdutoService.buscaPorId(pedido.getRestaurante().getId(),
                    itemPedido.getProduto().getId());

            itemPedido.setProduto(produto);
            itemPedido.setPedido(pedido);
            itemPedido.setPrecoUnitario(produto.getPreco());
        });
    }

    private void validaPedido(Pedido pedido) {
        Restaurante restaurante = this.cadastroRestauranteService.buscaPorId(pedido.getRestaurante().getId());
        Cidade cidade = this.cadastroCidadeService.buscarPorId(pedido.getEnderecoPedido().getCidade().getId());
        Usuario usuario = this.cadastroUsuarioService.buscaPorId(pedido.getCliente().getId());
        FormaPagamento formaPagamento = this.cadastroFormaPagamentoService.buscaPorId(pedido.getFormaPagamento().getId());

        pedido.setRestaurante(restaurante);
        pedido.getEnderecoPedido().setCidade(cidade);
        pedido.setCliente(usuario);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new RestauranteFormaPagamentoException(restaurante.getId(), formaPagamento.getId());
        }
    }
}
