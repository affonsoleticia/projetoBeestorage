package com.beestorageproject.techafropretas.request;

import com.beestorageproject.techafropretas.model.ProdutoModel;
import com.beestorageproject.techafropretas.model.SaidaProduto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class SaidaProdutoRequest {

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    public SaidaProdutoRequest(@NotNull Integer quantidade) {

        this.quantidade = quantidade;
    }
    public SaidaProduto toModelo(ProdutoModel produto){
        return new SaidaProduto(produto,quantidade);
    }

    public Integer getQuantidade() {
        return quantidade;
    }


}