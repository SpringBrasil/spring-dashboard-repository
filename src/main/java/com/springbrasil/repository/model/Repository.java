package com.springbrasil.repository.model;

import org.springframework.data.annotation.Id;

public class Repository {

	@Id
	private String id;
	private String url;
	private RepositoryType type;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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

}
