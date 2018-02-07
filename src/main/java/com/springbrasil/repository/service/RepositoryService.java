package com.springbrasil.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
