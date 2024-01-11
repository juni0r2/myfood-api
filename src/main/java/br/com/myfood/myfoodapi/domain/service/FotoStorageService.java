package br.com.myfood.myfoodapi.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    void remove(String nomeArquivoAntigo);

    InputStream recuperar(String nomeArquivo);

    default void substitui(String nomeArquivo, NovaFoto novaFoto) {
        this.armazenar(novaFoto);

        if (nomeArquivo != null) {
            this.remove(nomeArquivo);
        }
    }

    default String alteraNomeArquivo(String nomeAntigo) {
        return UUID.randomUUID().toString() + "_" + nomeAntigo;
    }

    @Getter
    @Builder
    class NovaFoto {
        private String nomeArquivo;
        private InputStream arquivo;
    }
}
