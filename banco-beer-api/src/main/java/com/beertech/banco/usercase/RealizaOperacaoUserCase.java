package com.beertech.banco.usercase;

import com.beertech.banco.dataprovider.ContaDataProvider;
import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.usercase.entity.Operacao;
import com.beertech.banco.usercase.entity.TipoOperacao;
import com.beertech.banco.usercase.exception.ContaException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RealizaOperacaoUserCase {

  private final ContaDataProvider contaDataProvider;

  @Autowired
  public RealizaOperacaoUserCase(ContaDataProvider contaDataProvider) {
    this.contaDataProvider = contaDataProvider;
  }

  public Conta realizaOperacao(String contaHash, Operacao operacao) {
    Optional<Conta> contaByHash = contaDataProvider.findByHash(contaHash);
    if(!contaByHash.isPresent()) {
      throw new ContaException("O hash da conta não existe!");
    }
    Conta conta = contaByHash.get();
    if(operacao.getTipo().equals(TipoOperacao.DEPOSITO))
      conta.deposito(operacao.getValor());
    else if(operacao.getTipo().equals(TipoOperacao.SAQUE))
      conta.saque(operacao.getValor());
    else
      throw new IllegalArgumentException("Operação não existente!");

    conta.addOperacao(operacao);
    contaDataProvider.save(conta);

    return conta;

  }

}
