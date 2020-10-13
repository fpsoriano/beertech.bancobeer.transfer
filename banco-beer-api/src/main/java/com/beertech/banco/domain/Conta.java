package com.beertech.banco.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.beertech.banco.domain.exception.ContaException;

public class Conta {

	private String hash;
	private List<Operacao> operacoes;
	private BigDecimal saldo;

	public Conta() {
	}

	public Conta(String hash) {
		this.hash = hash;
		this.operacoes = new ArrayList<Operacao>();
		saldo = new BigDecimal(0.00);
	}

	public Conta(String hash, List<Operacao> operacoes, BigDecimal saldo) {
		this.hash = hash;
		this.operacoes = operacoes;
		this.saldo = saldo;
	}

	public String getHash() {
		return hash;
	}

	public List<Operacao> getOperacoes() {
		return Collections.unmodifiableList(operacoes);
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void deposito(BigDecimal valor) {
		if (valor.compareTo(new BigDecimal(0)) <= 0)
			throw new ContaException("O valor para depósito deve ser maior do que 0!");
		this.saldo = saldo.add(valor);
	}

	public void saque(BigDecimal valor) {
		if (valor.compareTo(new BigDecimal(0)) <= 0)
			throw new ContaException("O valor para saque deve ser maior do que 0!");
		if (this.saldo.compareTo(valor) < 0)
			throw new ContaException("O valor para saque não pode ser maior do que o saldo!");
		this.saldo = this.saldo.subtract(valor);
	}

	public void addOperacao(Operacao operacao) {
		this.operacoes.add(operacao);
	}

}
