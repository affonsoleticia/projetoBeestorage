package com.beestorageproject.techafropretas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Saida")
public class SaidaProduto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ProdutoModel produto;
    
    @Column(nullable = false)
    private Integer quantidade;

    public SaidaProduto(ProdutoModel produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public SaidaProduto() {
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
