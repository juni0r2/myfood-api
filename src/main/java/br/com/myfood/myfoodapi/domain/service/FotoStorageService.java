package br.com.myfood.myfoodapi.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    @Getter
    @Builder
    class NovaFoto {
        private String nomeArquivo;
        private InputStream arquivo;
    }
}
