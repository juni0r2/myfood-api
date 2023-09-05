package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.api.model.FormaPagmentoModel;
import br.com.myfood.myfoodapi.domain.exception.EntidadeEmUsoException;
import br.com.myfood.myfoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.myfood.myfoodapi.domain.exception.FormaPagamentoNaoEncontrado;
import br.com.myfood.myfoodapi.domain.model.FormaPagamento;
import br.com.myfood.myfoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroFormaPagamentoService {

    public static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma Pagamento de código %d não pode ser removido, " +
            "pois está em uso";

    @Autowired
    private FormaPagamentoRepository repository;

    public List<FormaPagamento> lista() {
        return this.repository.findAll();
    }

    public FormaPagamento buscaPorId(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new FormaPagamentoNaoEncontrado(id));
    }

    public FormaPagamento salva(FormaPagamento formaPagamento) {
        return this.repository.save(formaPagamento);
    }

    public void remove(Long id) {
        try {
            FormaPagamento formaPagamento = this.buscaPorId(id);
            this.repository.delete(formaPagamento);
        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNaoEncontrado(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO,id));
        }
    }
}
