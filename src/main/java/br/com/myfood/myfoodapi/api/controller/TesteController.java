package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.CozinhaRepository;
import br.com.myfood.myfoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Cozinha> listaTodasPorNome(String nome) {
        return this.cozinhaRepository.findTodasByNomeContaining(nome);
    }

    @GetMapping("/cozinha/unica")
    public Optional<Cozinha> cozinhaPorNome(String nome) {
        return this.cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/restaurantes/taxa-frete")
    public List<Restaurante> listaTaxaFrente(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return this.restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> listaPorNomeRestauranteECozinhaId(String nome, Long cozinhaId) {
        return this.restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }
}
