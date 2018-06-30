package com.springbrasil.repository.controller.response;

import com.springbrasil.repository.model.Repository;

public class RepositoryCreateResponse {
	
	private String id;
	
	public RepositoryCreateResponse() {}
	
	public RepositoryCreateResponse(Repository repository) {
		this.id = repository.getId();
	}
	
	public String getId() {
		return id;
	}
	
	
}
