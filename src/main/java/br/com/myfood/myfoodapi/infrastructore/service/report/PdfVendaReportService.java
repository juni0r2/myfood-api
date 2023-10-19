package br.com.myfood.myfoodapi.infrastructore.service.report;

import br.com.myfood.myfoodapi.domain.model.Pedido;
import br.com.myfood.myfoodapi.domain.model.StatusPedido;
import br.com.myfood.myfoodapi.domain.model.dto.VendaDiaria;
import br.com.myfood.myfoodapi.domain.service.VendaQueryService;
import br.com.myfood.myfoodapi.domain.service.VendaReportService;
import br.com.myfood.myfoodapi.domain.service.filter.VendaDiariafilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
public class PdfVendaReportService implements VendaReportService {

    @Autowired
    VendaQueryService vendaQueryService;

    @Override
    public byte[] emitirVendasDiarias(VendaDiariafilter filtro, String timeOffset) {

        try {
            var inputStreamReport = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");
            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCATE", new Locale("pt", "Br"));

            var vendasDiarias = this.vendaQueryService.consultarVendasDiarias(filtro, timeOffset);

            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);
            var jasperPrint = JasperFillManager.fillReport(inputStreamReport, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório", e);
        }
    }
}
