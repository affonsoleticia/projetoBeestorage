package com.beestorageproject.techafropretas.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "tb_produtos")

public class ProdutoModel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank
	@Size(min=5, max=100, message = "O atributo nome do produto deve ter no mínimo 5 e no máximo 100 caracteres! ")
	private String nomeProduto;
	
	@Column
	@NotBlank
	@Size(min=5, max=1000, message = "O atributo descrição deve ter no mínimo 5 e no máximo 1000 caracteres! ")
	private String descricao;
	
	@Column
	@NotNull
	private Double preco;
	
	@Column
	@NotNull
	private Integer quantidadeMaxima;
	
	@Column
	@NotNull
	private Integer estoqueMinimo;
	
	@ManyToOne
	@JsonIgnoreProperties ("produto")
	private CategoriaModel categorias;
	
	@ManyToOne
	@JsonIgnoreProperties ("usuario")
	private UsuarioModel usuario;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<SaidaProduto> saidas= new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<EntradaProduto> entradas= new ArrayList<>();
    

    public ProdutoModel(String nomeProduto, String descricao, Double preco, Integer quantidadeMaxima, Integer estoqueMinimo){
    	this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeMaxima = quantidadeMaxima;
        this.estoqueMinimo = estoqueMinimo;
    }
    
	public CategoriaModel getCategorias() {
		return categorias;
	}

	public void setCategorias(CategoriaModel categorias) {
		this.categorias = categorias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidadeMaxima() {
		return quantidadeMaxima;
	}

	public void setQuantidadeMaxima(Integer quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}

	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}	
	
	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public void adicionarEntrada(EntradaProduto entrada){
        this.entradas.add(entrada);
    }
    public void adicionarSaida(SaidaProduto saida){
        this.saidas.add(saida);
    }
	
	
}
