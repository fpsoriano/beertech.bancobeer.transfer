package com.beertech.banco.dataprovider.repository;

import com.beertech.banco.dataprovider.model.MySqlConta;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ContaRepository extends CrudRepository<MySqlConta, Long> {

	Optional<MySqlConta> findByHash(String hash);
	
}
