package br.com.myfood.myfoodapi.infrastructore.service;

import br.com.myfood.myfoodapi.domain.model.dto.VendaDiaria;
import br.com.myfood.myfoodapi.domain.service.VendaQueryService;
import br.com.myfood.myfoodapi.domain.service.filter.VendaDiariafilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaQueryServiceImpl implements VendaQueryService {
    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariafilter filtro) {
        return null;
    }
}
