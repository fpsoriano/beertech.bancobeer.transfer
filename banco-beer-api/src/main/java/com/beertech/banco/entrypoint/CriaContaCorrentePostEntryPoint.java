package com.beertech.banco.entrypoint;

import com.beertech.banco.entrypoint.controller.model.ContaRequest;
import com.beertech.banco.usercase.CriarContaUserCase;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.exception.ContaException;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/banco")
public class CriaContaCorrentePostEntryPoint {

  private final CriarContaUserCase criarContaUserCase;

  @Autowired
  public CriaContaCorrentePostEntryPoint(CriarContaUserCase criarContaUserCase){
    this.criarContaUserCase = criarContaUserCase;
  }

  @PostMapping("/conta")
  public ResponseEntity criaContaCorrente(@Valid ContaRequest contaDto) {
    try {
      Conta conta = new Conta(contaDto.getHash());
      conta = criarContaUserCase.criarConta(conta);
      return ResponseEntity.ok(conta);
    } catch (ContaException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

}
