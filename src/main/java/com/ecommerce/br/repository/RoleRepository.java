package com.ecommerce.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.br.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long>{
	Role findByNome(String nome);
	
}
