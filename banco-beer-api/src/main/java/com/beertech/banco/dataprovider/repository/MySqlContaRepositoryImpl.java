package com.beertech.banco.dataprovider.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.dataprovider.ContaDataProvider;
import com.beertech.banco.dataprovider.model.MySqlConta;

@Repository
public class MySqlContaRepositoryImpl implements ContaDataProvider {

	private ContaRepository contaRepository;
		
	@Autowired
	public MySqlContaRepositoryImpl(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

	@Override
	public Optional<Conta> findByHash(String hash) {
		return contaRepository.findByHash(hash).map(new MySqlConta()::toDomain);
	}

	@Override
	public Conta save(Conta conta) {
		return new MySqlConta().toDomain((contaRepository.save(new MySqlConta().fromDomain(conta))));
	}

}
