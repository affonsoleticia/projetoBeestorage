package com.beestorageproject.techafropretas.response;

import com.beestorageproject.techafropretas.model.SaidaProduto;

public class SaidaProdutoResponse {
    private Long produto;
    private Integer quantidadeSaida;

    public SaidaProdutoResponse(SaidaProduto saidaProduto) {
        this.produto=saidaProduto.getProduto().getId();
        this.quantidadeSaida=saidaProduto.getQuantidade();
    }

    public Long getProduto() {
        return produto;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }
}
