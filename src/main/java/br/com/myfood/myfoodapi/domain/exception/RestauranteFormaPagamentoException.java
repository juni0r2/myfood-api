package br.com.myfood.myfoodapi.domain.exception;

public class RestauranteFormaPagamentoException extends NegocioException {
    public RestauranteFormaPagamentoException(Long restauranteId, Long formaPagamentoId) {
        super(String.format("Restaurante com id %d,, não aceita a forma de pagamento com id: %d", restauranteId, formaPagamentoId));
    }
}
