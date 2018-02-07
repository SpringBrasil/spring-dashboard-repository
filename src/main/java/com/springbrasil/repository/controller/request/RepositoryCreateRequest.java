package com.springbrasil.repository.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.springbrasil.repository.model.Repository;
import com.springbrasil.repository.model.RepositoryType;

public class RepositoryCreateRequest {
	
	@NotBlank
	private String url;
	@NotNull
	private RepositoryType type;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RepositoryType getType() {
		return type;
	}

	public void setType(RepositoryType type) {
		this.type = type;
	}

	public Repository toModel() {
		Repository repository = new Repository();
		repository.setUrl(this.url);
		repository.setType(this.type);
		return repository;
	}
	
}
