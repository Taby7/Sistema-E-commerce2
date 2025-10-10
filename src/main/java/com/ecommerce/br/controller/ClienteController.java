package com.ecommerce.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.br.entity.Cliente;

import com.ecommerce.br.service.ClienteService;

@Controller
@RequestMapping("/cliente")

public class ClienteController {
	
	@Autowired
	protected ClienteService clienteService;
	
	@GetMapping("/form")
	public String home(Model model ) {
		model.addAttribute("cliente", new Cliente ());
		return "cadastrarCliente";
	}
	
	@PostMapping("save")
	public String saveCliente(@ModelAttribute Cliente cliente, Model model) {
		if(clienteService.existsByEmail(cliente.getEmail())) {
			model.addAttribute("mesagemErro" , "Cliente com email" + cliente.getEmail() + "já cadastrado");
			return "cadastrarCliente";
		}else {
			clienteService.save(cliente);
		}
		model.addAttribute("mesagemSucesso", "Cliente com email " + cliente.getEmail() + "cadastrado com sucesso.");
		return "cadastrarCliente";
	}
	
	@GetMapping("/edit/{id}")
	public String editCliente(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.findById(id);
		model.addAttribute("cliente", cliente);
		return "editarCliente";
	}
	
	@GetMapping("list")
	public String listClientes(Model model) {
		model.addAttribute("clientes", clienteService.findAll());
		return "listarCliente";
	}
	
	@PostMapping("/edit")
	public String editClientePost(@ModelAttribute Cliente cliente, Model model) {
		if((!clienteService.findById(cliente.getId()).getEmail().equals(cliente.getEmail())) && clienteService.existsByEmail(cliente.getEmail())) {
			model.addAttribute("mensagemErro", "Cliente com email " + cliente.getEmail() + " já existe.");
			return listClientes(model);
		}else {
		clienteService.save(cliente);
		}
		
		model.addAttribute("mensagemSucesso", "Cliente com email " + cliente.getEmail() + " atualizado com sucesso!");
		return listClientes(model);

	}
	@GetMapping("/delete/{id}")
	public String deleteCliente(@PathVariable Long id, Model model) {
		clienteService.deleteById(id);
		model.addAttribute("mensagemSucesso",  "deletado com sucesso!");
		return listClientes(model);
	}

}
