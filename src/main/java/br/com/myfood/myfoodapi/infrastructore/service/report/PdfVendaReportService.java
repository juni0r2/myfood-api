package br.com.myfood.myfoodapi.infrastructore.service.report;

import br.com.myfood.myfoodapi.domain.model.Pedido;
import br.com.myfood.myfoodapi.domain.model.StatusPedido;
import br.com.myfood.myfoodapi.domain.model.dto.VendaDiaria;
import br.com.myfood.myfoodapi.domain.service.VendaQueryService;
import br.com.myfood.myfoodapi.domain.service.VendaReportService;
import br.com.myfood.myfoodapi.domain.service.filter.VendaDiariafilter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PdfVendaReportService implements VendaReportService {

    @Override
    public byte[] consultarVendasDiariasPdf(VendaDiariafilter filtro, String timeOffset) {
        return null;
    }
}
