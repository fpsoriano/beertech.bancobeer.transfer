package com.beertech.banco.entrypoint.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beertech.banco.dataprovider.repository.MySqlContaRepositoryImpl;

@Configuration
public class BeanConfiguration {

	@Autowired
	private MySqlContaRepositoryImpl mySqlContaRepositoryImpl;

}
