package br.com.myfood.myfoodapi.domain.service;

import br.com.myfood.myfoodapi.domain.exception.FotoProdutoNaoEncontradoException;
import br.com.myfood.myfoodapi.domain.exception.ProdutoNaoEncontradoException;
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
        String nomeArquivoExistente = null;

        Optional<FotoProduto> op = this.produtoRepository.findFotoById(restauranteId, produtoId);

        if (op.isPresent()) {
            nomeArquivoExistente = op.get().getNomeArquivo();
            this.produtoRepository.delete(op.get());
        }

        foto.setNomeArquivo(novoNomeArquivo);
        foto =  this.produtoRepository.save(foto);
        this.produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .arquivo(arquivo)
                .build();

        this.fotoStorageService.substitui(nomeArquivoExistente, novaFoto);

        return foto;
    }

    public FotoProduto buscarPorId(Long restauranteId, Long produtoId) {
        return this.produtoRepository.findFotoById(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradoException(restauranteId, produtoId));
    }
}
