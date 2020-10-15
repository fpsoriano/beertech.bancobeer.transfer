package com.beertech.banco.entrypoint.controller.model;

import javax.validation.constraints.NotBlank;

public class ContaRequest {
	@NotBlank
	private String hash;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
