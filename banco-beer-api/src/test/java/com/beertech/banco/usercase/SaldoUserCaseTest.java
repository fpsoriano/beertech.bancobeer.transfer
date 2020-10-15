package com.beertech.banco.usercase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.beertech.banco.dataprovider.ContaDataProvider;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.exception.ContaException;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaldoUserCaseTest {

  private ContaDataProvider contaDataProvider;
  private SaldoUserCase tested;

  @BeforeEach
  void setUp() {
    contaDataProvider = mock(ContaDataProvider.class);
    tested = new SaldoUserCase(contaDataProvider);
  }

  @Test
  void retornaSaldoComSucesso() {
    final Conta contaComSaldo = new Conta("hashValue");
    contaComSaldo.deposito(new BigDecimal(100));
    contaComSaldo.saque(new BigDecimal(10));
    when(contaDataProvider.findByHash("hashValue")).thenReturn(Optional.of(contaComSaldo));
    assertEquals(new BigDecimal(90), tested.saldo("hashValue"));
  }

  @Test
  void retornaSaldoComContaInexistente() {
    when(contaDataProvider.findByHash("hashValue")).thenReturn(Optional.ofNullable(null));
    assertThrows(ContaException.class, () -> {tested.saldo("hashValue");});
  }

}