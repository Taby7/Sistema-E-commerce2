package com.ecommerce.br.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.br.entity.Cliente;
import com.ecommerce.br.entity.ContaPagamento;

@Repository
public interface ContaPagamentoRepository extends JpaRepository<ContaPagamento, Long> {

	public boolean existsByNumeroCartao(String numeroCartao);

	public Object findByClienteId(Long clienteId);
	

}
