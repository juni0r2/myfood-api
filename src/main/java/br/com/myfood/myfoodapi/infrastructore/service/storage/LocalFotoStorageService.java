package br.com.myfood.myfoodapi.infrastructore.service.storage;

import br.com.myfood.myfoodapi.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Value("${myfood.storage.local.diretorio-fotos}")
    private Path diretorioFotos;


    @Override
    public void armazenar(NovaFoto novaFoto) {

        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
            FileCopyUtils.copy(novaFoto.getArquivo(), Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar o arquivo", e);
        }
    }

    @Override
    public void remove(String nomeArquivoAntigo) {
        try {
            Path path = getArquivoPath(nomeArquivoAntigo);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageException("Não foi possível remover o arquivo", e);
        }
    }

    @Override
    public InputStream recuperar(String nomeArquivo) {
        try {
            Path path = getArquivoPath(nomeArquivo);
            return Files.newInputStream(path);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return this.diretorioFotos.resolve(Path.of(nomeArquivo));
    }
}
