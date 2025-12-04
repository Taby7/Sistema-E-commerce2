package com.ecommerce.br.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable{
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotBlank(message= "nome do cliente é obrigatório")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message= "email do cliente é obrigatório")
	@Column(unique = true,nullable = false)
	private String email;
	
	@NotBlank(message= "telefone do cliente é obrigatório")
	@Column(nullable = false)
	private String telefone;
	
	@NotNull(message= "data de cadastro do cliente é obrigatório")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "cadastro",nullable = false)
	private LocalDate dataCadastro;
	
	@NotBlank(message= "endereço do cliente é obrigatório")
	@Column(nullable = false)
	private String endereco;
	
	public ContaPagamento getContaPagamento() {
		return contaPagamento;
	}

		public void setContaPagamento(ContaPagamento contaPagamento) {
	        this.contaPagamento = contaPagamento;
	        if (contaPagamento != null) {
	            contaPagamento.setCliente(this); 
	        }
	}

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, optional = true) 
    private ContaPagamento contaPagamento;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}