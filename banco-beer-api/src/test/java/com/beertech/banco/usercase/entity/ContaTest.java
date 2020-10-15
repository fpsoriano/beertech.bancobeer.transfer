package com.beertech.banco.usercase.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.beertech.banco.usercase.entity.Conta;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.beertech.banco.usercase.exception.ContaException;

class ContaTest {

	@Test
	void depositoComSucesso() {
		Conta conta = new Conta("hash");
		conta.deposito(new BigDecimal("1000.00"));
		assertEquals(new BigDecimal("1000.00"), conta.getSaldo());
	}
	
	@Test
	void depositoComValorInvalido() {
		Conta conta = new Conta("hash");
		assertThrows(ContaException.class, ()-> {conta.deposito(new BigDecimal("-1000.00"));});		
	}
	
	@Test
	void saqueComSucesso() {
		Conta conta = new Conta("hash");
		conta.deposito(new BigDecimal(1000.00));		
		conta.saque(new BigDecimal(1000.00));
		assertEquals(new BigDecimal(0.00), conta.getSaldo());		
	}
	
	@Test
	void saqueComValorInvalido() {
		Conta conta = new Conta("hash");
		conta.deposito(new BigDecimal(1000.00));
		assertThrows(ContaException.class, ()-> {conta.saque(new BigDecimal("-1000.00"));});		
	}
	
	@Test
	void saqueDeValorMaiorDoQueOSaldo() {
		Conta conta = new Conta("hash");
		assertThrows(ContaException.class, ()-> {conta.saque(new BigDecimal("1000.00"));});		
	}

}
