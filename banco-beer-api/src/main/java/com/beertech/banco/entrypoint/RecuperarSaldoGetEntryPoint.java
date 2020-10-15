package com.beertech.banco.entrypoint;

import com.beertech.banco.usercase.SaldoUserCase;
import com.beertech.banco.usercase.exception.ContaException;
import java.math.BigDecimal;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banco")
public class RecuperarSaldoGetEntryPoint {


  SaldoUserCase saldoUserCase;
  
  @Autowired
  public RecuperarSaldoGetEntryPoint(final SaldoUserCase saldoUserCase) {
    this.saldoUserCase = saldoUserCase;
  }

  @GetMapping(value = "/saldo/{hash}")
  public ResponseEntity recuperar(@PathVariable String hash) throws JSONException {
    try {
      BigDecimal saldo = saldoUserCase.saldo(hash);
      return ResponseEntity.ok(saldo);
    } catch(ContaException ex) {
      ex.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

}
