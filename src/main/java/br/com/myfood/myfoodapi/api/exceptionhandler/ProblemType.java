package br.com.myfood.myfoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada","Entidade não encontrada"),
    ENTIDADE_EM_USO("/enidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro inválido");

    private String uri;
    private String title;

    ProblemType(String path, String title) {
        this.uri = "https://myfood-api.com.br"+path;
        this.title = title;
    }
}
