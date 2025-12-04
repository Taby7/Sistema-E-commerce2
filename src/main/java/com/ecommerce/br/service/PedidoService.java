package com.ecommerce.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.br.entity.Cliente;
import com.ecommerce.br.entity.Pedido;
import com.ecommerce.br.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	protected PedidoRepository pedidoRepository;
	
	public Pedido save(Pedido p) {
	    if (p.getValidade() == null && p.getDataPedido() != null) {
	        // Define validade como 30 dias após a data do pedido
	        p.setValidade(p.getDataPedido().plusDays(30));
	    }
		return pedidoRepository.save(p);
	}
	
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	public Pedido findById(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("pedido não encontrado"));
	}
	
	public void deleteById(Long id) {
		if (!pedidoRepository.existsById(id)) {
			throw new RuntimeException("pedido não encontrado");
		}
		pedidoRepository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return pedidoRepository.existsById(id);
	}

	public boolean existsByCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}