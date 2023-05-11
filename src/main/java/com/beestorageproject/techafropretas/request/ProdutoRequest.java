package com.beestorageproject.techafropretas.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import com.beestorageproject.techafropretas.model.ProdutoModel;

import jakarta.validation.constraints.*;

public class ProdutoRequest {
    @NotBlank
    private String nomeProduto;
    @NotBlank
    private String descricao;
    @NotNull
    @PositiveOrZero
    private Integer quantidadeMaxima;
    @NotNull
    @Positive
    private Integer estoqueMinimo;

    public ProdutoRequest(String nomeProduto, String descricao, Integer quantidadeMaxima, Integer estoqueMinimo) {
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.quantidadeMaxima = quantidadeMaxima;
        this.estoqueMinimo = estoqueMinimo;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }
    public ProdutoModel toModelo(){
        return new ProdutoModel(nomeProduto,descricao,null, quantidadeMaxima,estoqueMinimo);
    }
}
