package com.ecommerce.br.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.ecommerce.br.entity.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PEDIDO")
public class Pedido implements Serializable {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message= "A data do pedido é obrigatória")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_pedido", nullable = false) 
	private LocalDate dataPedido;
	
	@NotBlank(message = "O status do pedido é obrigatório. Exemplo: em processamento, enviado, entregue, cancelado")
	@Column(nullable = false)
	private String status;
	
	@Column(name = "valor_total", nullable = false)
	private Double valorTotal;
	
	@NotBlank(message = "A lista de produtos é obrigatória.")
	@Column(name = "lista_produtos", nullable = false)
	private String listaProdutos;
	
	
	@ManyToOne
	@NotNull(message = "O cliente é obrigatório")
	private Cliente cliente;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "validade", nullable = true)
	private LocalDate validade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(String listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
}