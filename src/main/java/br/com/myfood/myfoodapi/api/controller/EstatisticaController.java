package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.domain.model.dto.VendaDiaria;
import br.com.myfood.myfoodapi.domain.service.VendaQueryService;
import br.com.myfood.myfoodapi.domain.service.filter.VendaDiariafilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

    @Autowired
    VendaQueryService vendaQueryService;

    List<VendaDiaria> consultarVendasDiarias(VendaDiariafilter filtro) {
        return vendaQueryService.consultarVendasDiarias(filtro);
    }
}
