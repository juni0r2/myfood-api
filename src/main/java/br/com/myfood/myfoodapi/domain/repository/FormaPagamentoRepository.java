package br.com.myfood.myfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myfood.myfoodapi.domain.model.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
    
}
