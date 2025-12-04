package com.ecommerce.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.br.entity.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
	Usuario findByUsername(String username);
	boolean existsByUsername(String username);

}
