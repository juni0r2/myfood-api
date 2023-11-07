package br.com.myfood.myfoodapi.domain.model;

import br.com.myfood.myfoodapi.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class FotoProduto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "produto_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;

    @NotBlank
    private String nomeArquivo;
    private String descricao;
    @NotBlank
    private String contentType;
    private Long tamanho;

    public Long getRestauranteId() {

        if (getProduto() != null) {
            return getProduto().getRestaurante().getId();
        }

        return null;
    }
}
