package br.com.myfood.myfoodapi.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {

    private LocalDate data;
    private Long totalVendas;
    private BigDecimal somaTotalVendas;
}
