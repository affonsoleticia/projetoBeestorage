package com.beestorageproject.techafropretas.response;

import com.beestorageproject.techafropretas.model.ProdutoModel;

public class ProdutoResponse {
    private Long id;
    private String nome;
    private Integer quantidade;

    public ProdutoResponse(ProdutoModel produto) {
        this.id = produto.getId();
        this.nome = produto.getNomeProduto();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNomeProduto() {
        return nome;
    }

}
