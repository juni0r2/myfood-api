package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.model.dto.VendaDiaria;
import br.com.myfood.myfoodapi.domain.service.filter.VendaDiariafilter;

import java.util.List;

public interface VendaReportService {

    byte[] consultarVendasDiariasPdf(VendaDiariafilter filtro, String timeOffset);
}
