package com.beestorageproject.techafropretas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beestorageproject.techafropretas.model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
	public List<CategoriaModel>findAllByCategoriasContainingIgnoreCase(String categoria);
}