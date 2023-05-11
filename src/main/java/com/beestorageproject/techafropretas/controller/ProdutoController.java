package com.beestorageproject.techafropretas.controller;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.beestorageproject.techafropretas.model.EntradaProduto;
import com.beestorageproject.techafropretas.model.ProdutoModel;
import com.beestorageproject.techafropretas.model.SaidaProduto;
import com.beestorageproject.techafropretas.repository.CategoriaRepository;
import com.beestorageproject.techafropretas.repository.ProdutoRepository;
import com.beestorageproject.techafropretas.request.EntradaProdutoRequest;
import com.beestorageproject.techafropretas.request.ProdutoRequest;
import com.beestorageproject.techafropretas.response.ProdutoResponse;
import com.beestorageproject.techafropretas.response.EntradaProdutoResponse;
import com.beestorageproject.techafropretas.response.SaidaProdutoResponse;
import com.beestorageproject.techafropretas.request.SaidaProdutoRequest;
import com.beestorageproject.techafropretas.validator.ExisteProduto;
import com.beestorageproject.techafropretas.handler.Erros;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;
    private final EntityManager manager;

    public ProdutoController(ProdutoRepository repository,EntityManager manager) {
        this.manager=manager;
        this.repository = repository;
    }
    
    @Autowired
	private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?>cadastrarProduto(@RequestBody @Valid ProdutoRequest produtoRequest, UriComponentsBuilder uriBuilder){
        ProdutoModel produto= produtoRequest.toModelo();
        repository.save(produto);
        URI uri=uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoResponse(produto));
    }
    @PostMapping("/{id}/entradas")
    @Transactional	
    public ResponseEntity<?> realizarEntrada(@RequestBody @Valid EntradaProdutoRequest entrada,@PathVariable Long id, UriComponentsBuilder uriBuilder){
        Optional<ProdutoModel> produto= repository.findById(id);
        if(produto.isEmpty()){
            Erros error=new Erros("Produto", "Não existe cadastro deste produto");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        EntradaProduto entradaProduto=entrada.toModelo(produto.get());
        manager.persist(entradaProduto);
        produto.get().adicionarEntrada(entradaProduto);
        URI uri=uriBuilder.path("/produtos/{id}/entradas/{idEntrada}").buildAndExpand(Map.of("id",produto.get().getId(),"idEntrada",entradaProduto.getId())).toUri();
        return ResponseEntity.created(uri).body(new EntradaProdutoResponse(entradaProduto));
    }
    @PostMapping("/{id}/saidas")
    @Transactional
    public ResponseEntity<?> realizarSaida(@RequestBody @Valid SaidaProdutoRequest saida, @PathVariable @ExisteProduto Long id, UriComponentsBuilder uriBuilder){
        Optional<ProdutoModel> produto= repository.findById(id);
        if(produto.isEmpty()){
            Erros error=new Erros("Produto", "Não existe cadastro deste produto");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Integer quantidadeDisponivel= repository.findByQuantidadeProduto(id);
        if (saida.getQuantidade()>quantidadeDisponivel){
            Erros error=new Erros("quantidade","Quantidade deve ser menor ou igual a "+quantidadeDisponivel);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        SaidaProduto saidaProduto=saida.toModelo(produto.get());
        manager.persist(saidaProduto);
        produto.get().adicionarSaida(saidaProduto);
        URI uri=uriBuilder.path("/produtos/{id}/saidas/{idSaida}").buildAndExpand(produto.get().getId(), saidaProduto.getId()).toUri();
        return ResponseEntity.created(uri).body(new SaidaProdutoResponse(saidaProduto));
    }
    
    @PostMapping
	public ResponseEntity<ProdutoModel> PostById(@RequestBody ProdutoModel produto){
		if (categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
			return ResponseEntity.notFound().build();
		
	}

	@PutMapping
	public ResponseEntity<ProdutoModel> putPostagem (@Valid @RequestBody ProdutoModel produto){


		if (repository.existsById(produto.getId())){
			
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(repository.save(produto));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}