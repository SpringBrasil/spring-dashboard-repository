package com.springbrasil.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.springbrasil.repository.dao.RepositoryDao;
import com.springbrasil.repository.model.Repository;

@Service
public class RepositoryService {
	
	@Autowired
	private RepositoryDao repositoryDao;
	
	public Repository save(Repository repository) {
		return repositoryDao.save(repository);
	}

	public Page<Repository> getAll(Integer page, Integer size) {
		return repositoryDao.findAll(new PageRequest(page, size));
	}

}
