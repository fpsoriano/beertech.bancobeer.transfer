package com.beertech.banco.dataprovider.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import com.beertech.banco.usercase.entity.Operacao;
import com.beertech.banco.usercase.entity.TipoOperacao;

@Embeddable
public class MySqlOperacao {

	private LocalDateTime dataHora;
	private BigDecimal valor;
	private TipoOperacao tipo;
	
	public MySqlOperacao() {
	}
	
	public MySqlOperacao(BigDecimal valor, TipoOperacao tipo, LocalDateTime dataHora) {
		this.valor = valor;
		this.tipo = tipo;
		this.dataHora = dataHora;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
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
	
	public MySqlOperacao fromDomain(Operacao operacao) {
		return new MySqlOperacao(operacao.getValor(), operacao.getTipo(), operacao.getDataHora());
	}
	
	public Operacao toDomain(MySqlOperacao mySqlOperacao) {
		return new Operacao(mySqlOperacao.getDataHora(), mySqlOperacao.getValor(), mySqlOperacao.getTipo());
	}
	
}
