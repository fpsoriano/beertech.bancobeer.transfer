package com.beertech.banco.usercase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beertech.banco.dataprovider.ContaDataProvider;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.exception.ContaException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CriarContaUserCaseTest {

  private ContaDataProvider contaDataProvider;
  private CriarContaUserCase tested;

  @BeforeEach
  void setUp() {
    contaDataProvider = mock(ContaDataProvider.class);
    tested = new CriarContaUserCase(contaDataProvider);
  }


  @Test
  void criarUmaContaComSucesso() {
    final Conta conta = new Conta("hash");

    final Conta id = tested.criarConta(conta);

    verify(contaDataProvider).save(any(Conta.class));
    assertNotNull(id);
  }

  @Test
  void nãoCriarUmaContaComHahsJaExistente() {
    final Conta conta = new Conta("hash");
    when(contaDataProvider.findByHash("hash")).thenReturn(Optional.of(new Conta("hash")));
    assertThrows(ContaException.class, () -> {tested.criarConta(conta);});
  }

}