package com.ecommerce.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.br.entity.Cliente;
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
           
            model.addAttribute("clientes", clienteService.findAll()); 
            return "cadastrarPedido";
        }
		
		if (pedido.getListaProdutos() == null || pedido.getListaProdutos().trim().isEmpty()) {
            model.addAttribute("mesagemErro", "A lista de produtos é obrigatória.");
            model.addAttribute("pedido", pedido);
            model.addAttribute("clientes", clienteService.findAll());
            return "cadastrarPedido";
        } 
		
		if (pedido.getValidade().isBefore(pedido.getDataPedido())) {
			model.addAttribute("mesagemErro", "A validade do pedido não pode ser anterior à data do pedido.");
			model.addAttribute("pedido", pedido);
			return "cadastrarPedido";
		}
        pedidoService.save(pedido);
        
        return "redirect:/pedido/list";
       
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
	
    // MÉTODO MODIFICADO: Lógica de validação simplificada e mensagem corrigida
	@PostMapping("/edit")
	public String editPedidoPost(@ModelAttribute Pedido pedido, Model model) {
		// A validação de negócio deve ser feita aqui, mas para fins de correção:
		
        // Adicionando um fallback se o cliente vier nulo (deve ser tratado com validação)
        if (pedido.getCliente() == null) {
             model.addAttribute("mensagemErro", "O cliente não pode ser nulo.");
             // Recarrega o form com o erro
             model.addAttribute("clientes", clienteService.findAll());
             return "editarPedido";
        }
        
        pedidoService.save(pedido);
		
        // Mensagem de sucesso corrigida para Pedido/Cliente
		model.addAttribute("mensagemSucesso", "Pedido " + pedido.getId() + " atualizado com sucesso!");
		return listPedidos(model);
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Pedido pedido = pedidoService.findById(id);
		List<Cliente> clientes = clienteService.findAll();
		if (pedido == null) {
			model.addAttribute("mensagemErro", "Pedido não encontrado."); // Mensagem corrigida
			return listPedidos(model);
		}
		model.addAttribute("pedido", pedido);
		model.addAttribute("clientes", clientes);
		return "editarPedido";
	}
}