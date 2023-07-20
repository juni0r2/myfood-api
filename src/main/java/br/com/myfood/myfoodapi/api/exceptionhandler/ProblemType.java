package br.com.myfood.myfoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada","Entidade não encontrada");

    private String uri;
    private String title;

    ProblemType(String path, String title) {
        this.uri = "https://myfood-api.com.br"+path;
        this.title = title;
    }
}
