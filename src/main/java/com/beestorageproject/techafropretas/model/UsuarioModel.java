package com.beestorageproject.techafropretas.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="tb_usuario")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column
	@NotNull(message = "O atributo nomeUsuario é obrigatório e não pode utilizar espaços em branco!")
	public String nomeUsuario;
	
	//@Schema(example = "email@email.com")
	@Column
	@NotNull(message = "O atributo usuário é obrigatório e não pode utilizar espaços em branco!") 
	@Email(message="O atributo e-mail é obrigatório")
	public String usuario;
	
	@Column
	@NotBlank(message = "O atributo senha é obrigatório e não pode utilizar espaços em branco!") 
	@Size(min = 8, message = "O atributo senha deve conter no mínimo 08 caracteres")
	public String senha;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List <ProdutoModel> produto;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
		
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
		
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;		
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public List<ProdutoModel> getProduto() {
		return produto;
	}
	public void setProduto(List<ProdutoModel> produto) {
		this.produto = produto;
	}

}
