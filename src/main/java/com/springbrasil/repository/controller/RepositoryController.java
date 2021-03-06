package com.springbrasil.repository.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springbrasil.repository.controller.request.RepositoryCreateRequest;
import com.springbrasil.repository.controller.response.RepositoryCreateResponse;
import com.springbrasil.repository.model.Repository;
import com.springbrasil.repository.service.RepositoryService;

@RestController
public class RepositoryController {

	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping(method = RequestMethod.POST, value = "/repositories", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public RepositoryCreateResponse createRepository(@RequestBody @Valid RepositoryCreateRequest request) {
		Repository savedRepository = repositoryService.save(request.toModel());
		return new RepositoryCreateResponse(savedRepository);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/repositories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Page<Repository> getRepositories(
			@RequestParam(required=false, defaultValue="0") Integer page,
			@RequestParam(required=false, defaultValue="10") Integer size) {
		return repositoryService.getAll(page, size);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/repositories/{repository_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Repository getRepositories(@PathVariable(name="repository_id") String repositoryId) {
		return repositoryService.get(repositoryId);
	}

}
