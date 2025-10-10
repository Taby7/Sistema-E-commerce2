package com.ecommerce.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.br.entity.ContaPagamento;
import com.ecommerce.br.service.ContaPagamentoService;

@Controller
@RequestMapping("/contaPagamento")
public class ContaPagamentoController {
	
	@Autowired
	protected ContaPagamentoService contaPagamentoService;
	
	@GetMapping("/form")
	public String home(Model model ) {
		model.addAttribute("contaPagamento", new ContaPagamento ());
		return "cadastrarContaPagamento";
	}
	
	@PostMapping("save")
	public String saveContaPagamento(@ModelAttribute ContaPagamento contapagamento, Model model) {
		if(contaPagamentoService.existsByNumeroCartao(contapagamento.getNumeroCartao())) {
			model.addAttribute("mesagemErro" , "Conta de pagamento com cartão" + contapagamento.getNumeroCartao() + "já cadastrado");
			return "cadastrarContaPagamento";
		}else {
			contaPagamentoService.save(contapagamento);
		}
		model.addAttribute("mesagemSucesso", "Conta de pagamento com cartão " + contapagamento.getNumeroCartao() + "cadastrado com sucesso.");
		return "cadastrarContaPagamento";
	}
	
	@GetMapping("list")
	public String listContaPagamentos(Model model) {
		model.addAttribute("contapagamentos", contaPagamentoService.findAll());
		return "listarContaPagamento";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteContaPagamento(@PathVariable Long id, Model model) {
		contaPagamentoService.deleteById(id);
		model.addAttribute("mensagemSucesso",  "deletado com sucesso!");
		return listContaPagamentos(model);
	}
	
	@PostMapping("/edit")
	public String editContaPagamentoPost(@ModelAttribute ContaPagamento contaPagamento, Model model) {
		if((!contaPagamentoService.findById(contaPagamento.getId()).getNumeroCartao().equals(contaPagamento.getNumeroCartao())) && contaPagamentoService.existsByNumeroCartao(contaPagamento.getNumeroCartao())) {
			model.addAttribute("mensagemErro", "Conta de pagamento com número de cartão " + contaPagamento.getNumeroCartao() + " já existe.");
			return listContaPagamentos(model);
		}else {
		contaPagamentoService.save(contaPagamento);
		}
		
		model.addAttribute("mensagemSucesso", "Conta de pagamento com número de cartão " + contaPagamento.getNumeroCartao() + " atualizado com sucesso!");
		return listContaPagamentos(model);

	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		ContaPagamento contaPagamento = contaPagamentoService.findById(id);
		if (contaPagamento == null) {
			model.addAttribute("mensagemErro", "Conta de pagamento não encontrada.");
			return listContaPagamentos(model);
		}
		model.addAttribute("contaPagamento", contaPagamento);
		return "editarContaPagamento";
	}
}