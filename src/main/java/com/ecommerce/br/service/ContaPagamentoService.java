package com.ecommerce.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.br.entity.Cliente;
import com.ecommerce.br.entity.ContaPagamento;
import com.ecommerce.br.repository.ContaPagamentoRepository;

@Service
public class ContaPagamentoService {
	
	@Autowired
	protected ContaPagamentoRepository contapagamentoRepository;
	
public ContaPagamento cadastrar(ContaPagamento conta) {
        
        // 1. Verifique se o Cliente já tem uma conta
        Long clienteId = conta.getCliente().getId();

        // Se você tiver um método de busca por cliente no seu repositório:
        if (contapagamentoRepository.findByClienteId(clienteId) != null) {
            throw new IllegalArgumentException("O cliente ID " + clienteId + " já possui uma conta de pagamento cadastrada.");
        }
        
        // Se a verificação passar, salve.
        return contapagamentoRepository.save(conta);
    }
	
	public ContaPagamento save(ContaPagamento c) {
		return contapagamentoRepository.save(c);
	}
	public List<ContaPagamento> findAll(){
		return contapagamentoRepository.findAll();
	}
	
	public void deleteById(Long id) {
		if (!contapagamentoRepository.existsById(id)) {
			throw new RuntimeException("conta não encontrado");
		}
		contapagamentoRepository.deleteById(id);
	}
	
	public ContaPagamento findById(Long id) {
		return contapagamentoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("conta não encontrada"));
	}
	
	public boolean existsByNumeroCartao(String numeroCartao) {
		return contapagamentoRepository.existsByNumeroCartao(numeroCartao);
	}

	
}
