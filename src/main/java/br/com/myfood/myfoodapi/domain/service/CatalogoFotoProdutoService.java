package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.model.FotoProduto;
import br.com.myfood.myfoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.myfood.myfoodapi.domain.service.FotoStorageService.NovaFoto;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream arquivo) {

        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String novoNomeArquivo = this.fotoStorageService.alteraNomeArquivo(foto.getNomeArquivo());

        Optional<FotoProduto> op = this.produtoRepository.findFotoById(restauranteId, produtoId);

        op.ifPresent(fotoProduto -> this.produtoRepository.delete(fotoProduto));

        foto.setNomeArquivo(novoNomeArquivo);
        foto =  this.produtoRepository.save(foto);

        NovaFoto novaFoto = NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .arquivo(arquivo)
                .build();

        this.fotoStorageService.armazenar(novaFoto);

        return foto;
    }
}
