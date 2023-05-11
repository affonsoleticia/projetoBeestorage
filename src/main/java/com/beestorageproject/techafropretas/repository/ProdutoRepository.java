package com.beestorageproject.techafropretas.repository;
	

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beestorageproject.techafropretas.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    @Query(value = "select (select IFNULL(SUM(e.quantidade),0) from entrada e where e.produto_id=?1)  -  IFNULL( SUM(s.quantidade) ,0) from saida s where s.produto_id=?1", nativeQuery = true)
    Integer findByQuantidadeProduto(Long idProduto);
   
    public List<ProdutoModel> findByPrecoBetween(@Param("inicio")Double inicio,@Param("fim") Double fim);
	public List <ProdutoModel> findAllByNomeProdutoContainingIgnoreCase(@Param("nomeProduto") String nomeProduto);
}

