package com.beestorageproject.techafropretas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beestorageproject.techafropretas.model.CategoriaModel;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
	public List<CategoriaModel>findAllByCategoriasContainingIgnoreCase(String categoria);
}