package com.ecommerce.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.br.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	boolean existsByEmail(String email);

}
