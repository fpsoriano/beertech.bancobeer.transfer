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

class TransferenciaUserCaseTest {

  private ContaDataProvider contaDataProvider;
  private TransferenciaUserCase tested;
  private RealizaOperacaoUserCase realizaOperacao;

  @BeforeEach
  void setUp() {
    contaDataProvider = mock(ContaDataProvider.class);
    tested = new TransferenciaUserCase(contaDataProvider);
    realizaOperacao = new RealizaOperacaoUserCase(contaDataProvider);
  }

  @Test
  void realizaTransferenciaComSucesso() {
    final Conta contaOrigem = new Conta("hashOrigem");
    final Conta contaDestino = new Conta("hashDestino");
    final Operacao deposito = new Operacao(new BigDecimal("50.40"), TipoOperacao.DEPOSITO);
    when(contaDataProvider.findByHash("hashOrigem")).thenReturn(Optional.of(contaOrigem));
    when(contaDataProvider.findByHash("hashDestino")).thenReturn(Optional.of(contaDestino));
    when(contaDataProvider.save(contaOrigem)).thenReturn(new Conta());
    when(contaDataProvider.save(contaDestino)).thenReturn(new Conta());
    realizaOperacao.realizaOperacao("hashOrigem", deposito);
    tested.transferencia("hashOrigem", "hashDestino", new BigDecimal("40.40"));
    assertEquals(new BigDecimal("40.40"), contaDestino.getSaldo());
    assertEquals(new BigDecimal("10.00"), contaOrigem.getSaldo());
  }

  @Test
  void naoRealizaTransferenciaComSaldoInsuficiente() {
    final Conta contaOrigem = new Conta("hashOrigem");
    final Conta contaDestino = new Conta("hashDestino");
    when(contaDataProvider.findByHash("hashOrigem")).thenReturn(Optional.of(contaOrigem));
    when(contaDataProvider.findByHash("hashDestino")).thenReturn(Optional.of(contaDestino));
    when(contaDataProvider.save(contaOrigem)).thenReturn(new Conta());
    when(contaDataProvider.save(contaDestino)).thenReturn(new Conta());
    assertThrows(
        ContaException.class, ()-> {tested.transferencia("hashOrigem", "hashDestino", new BigDecimal("40.40"));});
  }

  @Test
  void naoRealizaTransferenciaComContaOrigemInvalida() {
    when(contaDataProvider.findByHash("hashOrigem")).thenReturn(Optional.ofNullable(null));
    assertThrows(ContaException.class, ()-> {tested.transferencia("hashOrigem", "hashDestino", new BigDecimal("40.40"));});
  }

  @Test
  void naoRealizaTransferenciaComContaDestinoInvalida() {
    final Conta contaOrigem = new Conta("hashOrigem");
    when(contaDataProvider.findByHash("hashOrigem")).thenReturn(Optional.of(contaOrigem));
    when(contaDataProvider.findByHash("hashDestino")).thenReturn(Optional.ofNullable(null));;
    assertThrows(ContaException.class, ()-> {tested.transferencia("hashOrigem", "hashDestino", new BigDecimal("40.40"));});
  }

  @Test
  void naoRealizaTransferenciaComValorInvalido() {
    final Conta contaOrigem = new Conta("hashOrigem");
    final Conta contaDestino = new Conta("hashDestino");
    when(contaDataProvider.findByHash("hashOrigem")).thenReturn(Optional.of(contaOrigem));
    when(contaDataProvider.findByHash("hashDestino")).thenReturn(Optional.of(contaDestino));
    when(contaDataProvider.save(contaOrigem)).thenReturn(new Conta());
    when(contaDataProvider.save(contaDestino)).thenReturn(new Conta());
    assertThrows(ContaException.class, ()-> {tested.transferencia("hashOrigem", "hashDestino", new BigDecimal("0.00"));});
  }

}