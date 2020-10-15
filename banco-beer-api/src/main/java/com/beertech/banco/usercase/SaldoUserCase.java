package com.beertech.banco.usercase;

import com.beertech.banco.dataprovider.ContaDataProvider;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.exception.ContaException;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaldoUserCase {

  private final ContaDataProvider contaDataProvider;

  @Autowired
  public SaldoUserCase(ContaDataProvider contaDataProvider) {
    this.contaDataProvider = contaDataProvider;
  }

  public BigDecimal saldo(String hash) {
    Optional<Conta> conta = contaDataProvider.findByHash(hash);
    if(!conta.isPresent()) {
      throw new ContaException("O hash da conta não existe!");
    }
    return conta.get().getSaldo();
  }

}
