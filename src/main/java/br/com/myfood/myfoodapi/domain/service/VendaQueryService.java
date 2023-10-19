package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.model.dto.VendaDiaria;
import br.com.myfood.myfoodapi.domain.service.filter.VendaDiariafilter;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariafilter filtro, String timeOffset);
}
