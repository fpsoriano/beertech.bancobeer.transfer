package com.beertech.banco.dataprovider.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.beertech.banco.usercase.entity.Conta;
import com.beertech.banco.dataprovider.model.MySqlConta;
import com.beertech.banco.dataprovider.model.MySqlOperacao;

class MySqlContaRepositoryImplTest {
	
	private ContaRepository contaRepository;
	MySqlContaRepositoryImpl testClass;

	@BeforeEach
    void setUp() {
		contaRepository = mock(ContaRepository.class);
		testClass = new MySqlContaRepositoryImpl(contaRepository);
	}
	
	@Test
	void findByHashSuccess() {
		when(contaRepository.findByHash("hash")).thenReturn(Optional.of(new MySqlConta(1l, "hash", new ArrayList<MySqlOperacao>(), new BigDecimal(10.0))));
		Optional<Conta> findByHash = testClass.findByHash("hash");
		assertNotNull(findByHash.get());
	}

}
