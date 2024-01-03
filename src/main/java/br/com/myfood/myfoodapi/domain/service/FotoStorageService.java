package br.com.myfood.myfoodapi.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    default String alteraNomeArquivo(String nomeAntigo) {
        return UUID.randomUUID().toString()+"_"+nomeAntigo;
    }

    @Getter
    @Builder
    class NovaFoto {
        private String nomeArquivo;
        private InputStream arquivo;
    }
}
