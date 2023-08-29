package br.com.myfood.myfoodapi.api.mode.mixin;

import br.com.myfood.myfoodapi.core.validation.Groups;
import br.com.myfood.myfoodapi.domain.model.Cozinha;
import br.com.myfood.myfoodapi.domain.model.Endereco;
import br.com.myfood.myfoodapi.domain.model.FormaPagamento;
import br.com.myfood.myfoodapi.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
