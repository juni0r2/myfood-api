package br.com.myfood.myfoodapi.api.model.mixin;

import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Endereco;
import br.com.myfood.myfoodapi.domain.model.FormaPagamento;
import br.com.myfood.myfoodapi.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;

public abstract class RestaurenteMixin {

    //    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    //    @JsonIgnore
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<Produto> produtos;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento;
}
