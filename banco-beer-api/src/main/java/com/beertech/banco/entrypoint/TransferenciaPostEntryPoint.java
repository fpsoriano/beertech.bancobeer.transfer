package com.beertech.banco.entrypoint;

import com.beertech.banco.entrypoint.controller.model.TransferenciaRequest;
import com.beertech.banco.usercase.TransferenciaUserCase;
import com.beertech.banco.usercase.exception.ContaException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TransferenciaPostEntryPoint {

  @Autowired
  TransferenciaUserCase transferenciaUserCase;

  @PostMapping("/transferencia")
  public ResponseEntity transferencia(@Valid @RequestBody TransferenciaRequest transferenciaRequest) {
    try {
      transferenciaUserCase.transferencia(transferenciaRequest.getContaOrigem(), transferenciaRequest.getContaDestino(), transferenciaRequest
          .getValor());
      return ResponseEntity.ok().build();
    } catch (ContaException | IllegalArgumentException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

}
