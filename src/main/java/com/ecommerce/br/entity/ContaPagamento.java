package com.ecommerce.br.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_CONTAPAGAMENTO")

public class ContaPagamento implements Serializable {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotBlank(message= "limite do cartão é obrigatório")
	@Column(nullable = false)
	private String limiteCartao;
	
	@NotBlank(message= "número do cartão é obrigatório")
	@Column(unique = true,nullable = false)
	private String numeroCartao;
	
	@NotBlank(message= "nome do titular do cartão é obrigatório")
	@Column(nullable = false)
	private String titular;
	
	@NotNull(message= "data de validade do cartão é obrigatório")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "validade",nullable = false)
	private LocalDate dataValidadeCartao;
	
	@NotBlank(message= "bandeira do cartão é obrigatório!Exemplo: visa,mastercard,elo,etc")
	@Column(nullable = false)
	private String bandeira;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getLimiteCartao() {
		return limiteCartao;
	}
	public void setLimiteCartao(String limiteCartao) {
		this.limiteCartao = limiteCartao;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public LocalDate getDataValidadeCartao() {
		return dataValidadeCartao;
	}
	public void setDataValidadeCartao(LocalDate dataValidadeCartao) {
		this.dataValidadeCartao = dataValidadeCartao;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	
	

}