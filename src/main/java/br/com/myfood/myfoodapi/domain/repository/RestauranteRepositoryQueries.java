package br.com.myfood.myfoodapi.domain.repository;

import br.com.myfood.myfoodapi.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {
    List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
    List<Restaurante> findCustomizado(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
    List<Restaurante> findCriteira(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
    List<Restaurante> findComFreteGratisLazy(String nome);
}
