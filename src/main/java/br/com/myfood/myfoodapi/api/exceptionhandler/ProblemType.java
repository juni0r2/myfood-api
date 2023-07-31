package br.com.myfood.myfoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado","Recurso não encontrado"),
    ENTIDADE_EM_USO("/enidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro inválido"),
    ERRO_DE_SISTEMA("/erro-de-sistema","Erro de Sistema");

    private String uri;
    private String title;

    ProblemType(String path, String title) {
        this.uri = "https://myfood-api.com.br"+path;
        this.title = title;
    }
}
