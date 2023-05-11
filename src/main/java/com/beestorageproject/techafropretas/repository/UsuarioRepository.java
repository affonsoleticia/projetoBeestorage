package com.beestorageproject.techafropretas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.beestorageproject.techafropretas.model.UsuarioModel;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
	public Optional<UsuarioModel> findByUsuario(@Param ("usuario")String usuario);

}