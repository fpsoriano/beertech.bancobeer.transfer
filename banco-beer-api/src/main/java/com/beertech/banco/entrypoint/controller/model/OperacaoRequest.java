package com.beertech.banco.entrypoint.controller.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.beertech.banco.usercase.entity.TipoOperacao;

public class OperacaoRequest {

	@NotBlank
	private String hash;
	@NotNull
	@Min(value = 0, message = "Valor deve ser maior do que 0!")
	private BigDecimal valor;
	@NotNull
	private TipoOperacao tipo;
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoOperacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoOperacao tipo) {
		this.tipo = tipo;
	}
	
	
}
