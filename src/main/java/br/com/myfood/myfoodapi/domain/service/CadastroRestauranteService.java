package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.exception.RestauranteNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.model.Cidade;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.FormaPagamento;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroRestauranteService {

    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Nenhum restaurante encontrado com id: %d";
    public static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    public List<Restaurante> listar() {
        List<Restaurante> lista = this.restauranteRepository.findAll();
        lista.get(0).getCozinha().getNome();
        return lista;
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {

        Long idCozinha = restaurante.getCozinha().getId();
        Long idCidade = restaurante.getEndereco().getCidade().getId();
        Cozinha cozinha = this.cozinhaService.buscaPorId(idCozinha);
        Cidade cidade = this.cadastroCidadeService.buscarPorId(idCidade);
        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return this.restauranteRepository.save(restaurante);
    }

    public Restaurante buscaPorId(Long id) {
        return this.restauranteRepository.findById(id)
            .orElseThrow(() -> new RestauranteNaoEncontradoException(
                    String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id)));
    }

    @Transactional
    public void remover(Long id) {

        try {
            Restaurante restaurante = this.buscaPorId(id);
            this.restauranteRepository.delete(restaurante);
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO,id));
        }
    }

    @Transactional
    public void ativar(Long id) {
        Restaurante restaurante = this.buscaPorId(id);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long id) {
        Restaurante restaurante = this.buscaPorId(id);
        restaurante.inativar();
    }

    @Transactional
    public void associa(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = this.buscaPorId(restauranteId);
        FormaPagamento formaPagamento = this.cadastroFormaPagamentoService.buscaPorId(formaPagamentoId);

        restaurante.adicionaFormaPagamento(formaPagamento);
    }

    @Transactional
    public void desassocia(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = this.buscaPorId(restauranteId);
        FormaPagamento formaPagamento = this.cadastroFormaPagamentoService.buscaPorId(formaPagamentoId);

        restaurante.removeFormaPagamento(formaPagamento);
    }
}
