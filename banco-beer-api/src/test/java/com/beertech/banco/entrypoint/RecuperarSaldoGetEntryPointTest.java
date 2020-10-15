package com.beertech.banco.entrypoint;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beertech.banco.entrypoint.controller.model.OperacaoRequest;
import com.beertech.banco.usercase.CriarContaUserCase;
import com.beertech.banco.usercase.SaldoUserCase;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.entity.TipoOperacao;
import java.math.BigDecimal;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class RecuperarSaldoGetEntryPointTest {

  private SaldoUserCase saldoUserCase;
  private RecuperarSaldoGetEntryPoint tested;

  @BeforeEach
  void setUp() {
    saldoUserCase = mock(SaldoUserCase.class);
    tested = new RecuperarSaldoGetEntryPoint(saldoUserCase);
  }

  @Test
  void recuperarConta() throws JSONException {
    when(saldoUserCase.saldo(any(String.class))).thenReturn(BigDecimal.ONE);
    ResponseEntity responseEntity = tested.recuperar("hash");
    verify(saldoUserCase).saldo(any(String.class));
    assertNotNull(responseEntity);
    assertEquals(BigDecimal.ONE, responseEntity.getBody());
  }

}