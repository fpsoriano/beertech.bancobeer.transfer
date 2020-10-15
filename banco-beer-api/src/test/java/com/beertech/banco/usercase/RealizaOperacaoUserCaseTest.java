package com.beertech.banco.usercase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.beertech.banco.dataprovider.ContaDataProvider;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.entity.Operacao;
import com.beertech.banco.usercase.entity.TipoOperacao;
import com.beertech.banco.usercase.exception.ContaException;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RealizaOperacaoUserCaseTest {

  private ContaDataProvider contaDataProvider;
  private RealizaOperacaoUserCase tested;

  @BeforeEach
  void setUp() {
    contaDataProvider = mock(ContaDataProvider.class);
    tested = new RealizaOperacaoUserCase(contaDataProvider);
  }

  @Test
  void realizaOperacaoDeposito() {
    final Conta conta = new Conta("hash");
    final Operacao deposito = new Operacao(new BigDecimal("1050.90"), TipoOperacao.DEPOSITO);
    when(contaDataProvider.findByHash("hash")).thenReturn(Optional.of(conta));
    when(contaDataProvider.save(conta)).thenReturn(new Conta());
    tested.realizaOperacao("hash", deposito);
    assertEquals(new BigDecimal("1050.90"), conta.getSaldo());
    assertEquals(1, conta.getOperacoes().size());

  }

  @Test
  void realizaOperacaoSaque() {
    final Conta conta = new Conta("hash");
    final Operacao deposito = new Operacao(new BigDecimal("1050.90"), TipoOperacao.DEPOSITO);
    final Operacao saque = new Operacao(new BigDecimal("50.40"), TipoOperacao.SAQUE);
    when(contaDataProvider.findByHash("hash")).thenReturn(Optional.of(conta));
    when(contaDataProvider.save(conta)).thenReturn(new Conta());
    tested.realizaOperacao("hash", deposito);
    tested.realizaOperacao("hash", saque);
    assertEquals(new BigDecimal("1000.50"), conta.getSaldo());
    assertEquals(2, conta.getOperacoes().size());

  }

  @Test
  void naoRealizaOperacaoDepositoComValorInvalido() {
    final Conta conta = new Conta("hash");
    final Operacao deposito = new Operacao(new BigDecimal("0"), TipoOperacao.DEPOSITO);
    when(contaDataProvider.findByHash("hash")).thenReturn(Optional.of(conta));
    when(contaDataProvider.save(conta)).thenReturn(new Conta());
    assertThrows(ContaException.class, () -> {tested.realizaOperacao("hash", deposito);});

  }

  @Test
  void naoRealizaOperacaoSaqueComSaldoInsuficiente() {
    final Conta conta = new Conta("hash");
    final Operacao saque = new Operacao(new BigDecimal("50.40"), TipoOperacao.SAQUE);
    when(contaDataProvider.findByHash("hash")).thenReturn(Optional.of(conta));
    when(contaDataProvider.save(conta)).thenReturn(new Conta());
    assertThrows(ContaException.class, () -> {tested.realizaOperacao("hash", saque);});

  }


}