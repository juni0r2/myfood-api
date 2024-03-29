package br.com.myfood.myfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = Boolean.TRUE;

    @NotNull
    private Boolean aberto = Boolean.FALSE;

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> usuarios = new HashSet<>();

    public void ativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

    public void adicionaFormaPagamento(FormaPagamento formaPagamento) {
        this.formasPagamento.add(formaPagamento);
    }

    public void removeFormaPagamento(FormaPagamento formaPagamento) {
        this.formasPagamento.remove(formaPagamento);
    }

    public void abrir() {
        this.setAberto(true);
    }

    public void fechar() {
        this.setAberto(false);
    }

    public void adicionaUsuario(Usuario usuario) {
        this.getUsuarios().add(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        this.getUsuarios().remove(usuario);
    }

    public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().contains(formaPagamento);
    }

    public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
        return !getFormasPagamento().contains(formaPagamento);
    }
}
