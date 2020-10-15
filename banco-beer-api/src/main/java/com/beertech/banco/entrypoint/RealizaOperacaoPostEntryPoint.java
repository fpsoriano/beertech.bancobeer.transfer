package com.beertech.banco.entrypoint;

import com.beertech.banco.entrypoint.controller.model.OperacaoRequest;
import com.beertech.banco.usercase.RealizaOperacaoUserCase;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.entity.Operacao;
import com.beertech.banco.usercase.exception.ContaException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banco")
public class RealizaOperacaoPostEntryPoint {

  @Autowired
  RealizaOperacaoUserCase realizaOperacao;

  @PostMapping(value = "/operacao")
  public ResponseEntity realizaOperacao(@Valid @RequestBody OperacaoRequest operacaoRequest) {
    Operacao operacaoNaoRealizada = new Operacao(operacaoRequest.getValor(), operacaoRequest.getTipo());
    try {
      realizaOperacao.realizaOperacao(operacaoRequest.getHash(), operacaoNaoRealizada);
      return ResponseEntity.ok().build();
    } catch (ContaException | IllegalArgumentException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

}
