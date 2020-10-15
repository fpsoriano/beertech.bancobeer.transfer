package com.beertech.banco.entrypoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beertech.banco.entrypoint.controller.model.ContaRequest;
import com.beertech.banco.usercase.CriarContaUserCase;
import com.beertech.banco.usercase.entity.Conta;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class CriaContaCorrentePostEntryPointTest {

  private CriarContaUserCase criarContaUserCase;
  private CriaContaCorrentePostEntryPoint tested;

  @BeforeEach
  void setUp() {
    criarContaUserCase = mock(CriarContaUserCase.class);
    tested = new CriaContaCorrentePostEntryPoint(criarContaUserCase);
  }

  @Test
  void criarUmaConta() {
    final ContaRequest contaRequest  = new ContaRequest();
    contaRequest.setHash("hash");
    final Conta conta = new Conta("hash");
    when(criarContaUserCase.criarConta(any(Conta.class))).thenReturn(conta);
    final ResponseEntity responseEntity = tested.criaContaCorrente(contaRequest);
    verify(criarContaUserCase).criarConta(any(Conta.class));
    assertNotNull(responseEntity);
  }


}