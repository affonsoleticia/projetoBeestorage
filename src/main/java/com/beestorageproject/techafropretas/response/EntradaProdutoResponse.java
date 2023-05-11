package com.beestorageproject.techafropretas.response;

import com.beestorageproject.techafropretas.model.EntradaProduto;

public class EntradaProdutoResponse {
    private Long produto;
    private Integer quantidadeEntrada;

    public EntradaProdutoResponse(EntradaProduto entradaProduto) {
        this.produto=entradaProduto.getProduto().getId();
        this.quantidadeEntrada=entradaProduto.getQuantidade();
    }

    public Long getProduto() {
        return produto;
    }

    public Integer getQuantidadeEntrada() {
        return quantidadeEntrada;
    }
}