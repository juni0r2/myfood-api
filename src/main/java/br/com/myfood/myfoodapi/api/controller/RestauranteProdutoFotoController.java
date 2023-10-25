package br.com.myfood.myfoodapi.api.controller;

import br.com.myfood.myfoodapi.api.assembler.ProdutoInputDisassembler;
import br.com.myfood.myfoodapi.api.assembler.ProdutoModelAssembler;
import br.com.myfood.myfoodapi.api.model.ProdutoModel;
import br.com.myfood.myfoodapi.api.model.input.FotoProdutoInput;
import br.com.myfood.myfoodapi.api.model.input.ProdutoInput;
import br.com.myfood.myfoodapi.domain.model.Produto;
import br.com.myfood.myfoodapi.domain.model.Restaurante;
import br.com.myfood.myfoodapi.domain.repository.ProdutoRespositoy;
import br.com.myfood.myfoodapi.domain.service.CadastroProdutoService;
import br.com.myfood.myfoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarfoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, FotoProdutoInput fotoProdutoInput) {

        var nomeArquivo = UUID.randomUUID().toString() +"-"+ fotoProdutoInput.getArquivo().getOriginalFilename();
        var arquivoFoto = Path.of("C:\\java\\estudos\\catalogo",nomeArquivo);

        System.out.println(fotoProdutoInput.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoInput.getArquivo().getContentType());

        try {
            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
