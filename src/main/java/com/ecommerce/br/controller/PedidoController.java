package com.ecommerce.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.br.entity.Pedido;
import com.ecommerce.br.service.ClienteService;
import com.ecommerce.br.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	protected PedidoService pedidoService;
	
	 @Autowired 
	    private ClienteService clienteService; 
	    
	    @GetMapping("/form")
	    public String exibirFormulario(Model model) {
	       model.addAttribute("pedido", new Pedido());
	       model.addAttribute("clientes", clienteService.findAll()); 
	    return "cadastrarPedido";
	    }
	
	@PostMapping("save")
	public String savePedido(@ModelAttribute Pedido pedido, Model model) {
		if (pedido.getId() != null && pedidoService.existsById(pedido.getId())) {
            model.addAttribute("mesagemErro", "Pedido " + pedido.getId() + " já cadastrado");
            return "cadastrarPedido";
        }
        if (pedido.getListaProdutos() == null || pedido.getListaProdutos().trim().isEmpty()) {
            model.addAttribute("mesagemErro", "A lista de produtos é obrigatória.");
            model.addAttribute("pedido", pedido);
            model.addAttribute("clientes", clienteService.findAll());
            return "cadastrarPedido";
        }
        pedidoService.save(pedido);
        return "redirect:/pedido/form";
	}
	@GetMapping("list")
	public String listPedidos(Model model) {
		model.addAttribute("pedidos", pedidoService.findAll());
		return "listarPedido";
	}
	@GetMapping("/delete/{id}")
	public String deletePedido(@PathVariable Long id, Model model) {
		pedidoService.deleteById(id);
		model.addAttribute("mensagemSucesso",  "deletado com sucesso!");
		return listPedidos(model);
	}
	
	

}