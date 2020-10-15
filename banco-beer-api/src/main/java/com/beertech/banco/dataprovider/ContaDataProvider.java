package com.beertech.banco.dataprovider;

import java.util.Optional;

import com.beertech.banco.usercase.entity.Conta;

public interface ContaDataProvider {
	Optional<Conta> findByHash(String hash);
	Conta save(Conta conta);
}
