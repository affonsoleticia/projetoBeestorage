package com.beestorageproject.techafropretas.request;

import com.beestorageproject.techafropretas.model.EntradaProduto;
import com.beestorageproject.techafropretas.model.ProdutoModel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class EntradaProdutoRequest {
	
    @NotNull
    @Positive
    private Integer quantidade;
    
    @NotNull
    @PositiveOrZero
    private Double  precoUnitario;

    public EntradaProdutoRequest(@NotNull Integer quantidade, @NotNull Double precoUnitario) {

        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }
    public EntradaProduto toModelo(ProdutoModel produto){
        return new EntradaProduto(produto,precoUnitario,quantidade);
    }


    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

}