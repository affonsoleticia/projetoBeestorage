package com.beestorageproject.techafropretas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Entrada")
public class EntradaProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProdutoModel produto;

    @Column(nullable = false)
    private Double precoUnitario;

    @Column(nullable = false)
    private Integer quantidade;

    public EntradaProduto(ProdutoModel produto, Double precoUnitario, Integer quantidade) {
        this.produto = produto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public EntradaProduto() {

    }

    public Long getId() {
        return id;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
