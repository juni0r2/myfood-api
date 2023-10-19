package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.service.filter.VendaDiariafilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariafilter filtro, String timeOffset);
}
