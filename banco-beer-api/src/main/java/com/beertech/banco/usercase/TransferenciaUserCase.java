package com.beertech.banco.usercase;

import com.beertech.banco.dataprovider.ContaDataProvider;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.exception.ContaException;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferenciaUserCase {

  private final ContaDataProvider contaDataProvider;

  @Autowired
  public TransferenciaUserCase(ContaDataProvider contaDataProvider) {
    this.contaDataProvider = contaDataProvider;
  }

  public void transferencia(String hahsDaContaOrigem, String hahsDaContaDestino, BigDecimal valor) {
    Optional<Conta> contaOrigem = contaDataProvider.findByHash(hahsDaContaOrigem);
    Optional<Conta> contaDestino = contaDataProvider.findByHash(hahsDaContaDestino);

    if(!contaOrigem.isPresent() || !contaDestino.isPresent())
      throw new ContaException("Conta origem e/ou conta destino invalida!");

    if(valor.compareTo(new BigDecimal(0)) <= 0)
      throw new ContaException("O valor de transferencia deve ser maior que zero!");

    if(contaOrigem.get().getSaldo().compareTo(valor) < 0)
      throw new ContaException("O valor de transferencia deve ser menor ou igual ao saldo da conta de origem!");

    contaOrigem.get().saque(valor);
    contaDestino.get().deposito(valor);

    contaDataProvider.save(contaOrigem.get());
    contaDataProvider.save(contaDestino.get());
  }

}
