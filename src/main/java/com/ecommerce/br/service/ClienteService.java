package com.ecommerce.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.br.entity.Cliente;
import com.ecommerce.br.entity.Pedido;
import com.ecommerce.br.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	protected ClienteRepository clienteRepository;
	
	public Cliente save(Cliente t) {
		return clienteRepository.save(t);
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("cliente não encontrado"));
	}
	
	public void deleteById(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new RuntimeException("cliente não encontrado");
		}
		clienteRepository.deleteById(id);
	}
	
	public boolean existsByEmail(String email) {
		return clienteRepository.existsByEmail(email);
	}

	

}
