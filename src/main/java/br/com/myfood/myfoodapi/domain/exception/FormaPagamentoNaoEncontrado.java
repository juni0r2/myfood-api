package br.com.myfood.myfoodapi.domain.exception;

public class FormaPagamentoNaoEncontrado extends EntidadeNaoEncontradaException {
    public FormaPagamentoNaoEncontrado(String msg) {
        super(msg);
    }

    public FormaPagamentoNaoEncontrado(Long id) {
        this(String.format("Nenhuma forma de pagamento encontrada com id: %d",id));
    }
}
