package com.beertech.banco.usercase;

import com.beertech.banco.dataprovider.ContaDataProvider;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.exception.ContaException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriarContaUserCase {

  private final ContaDataProvider contaDataProvider;

  @Autowired
  public CriarContaUserCase(ContaDataProvider contaDataProvider) {
    this.contaDataProvider = contaDataProvider;
  }

  public Conta criarConta(Conta conta) {
    Optional<Conta> findByHash = contaDataProvider.findByHash(conta.getHash());
    if(findByHash.isPresent())
      throw new ContaException("Ja existe uma conta com esse valor de Hash");
    contaDataProvider.save(conta);
    return conta;
  }

}
