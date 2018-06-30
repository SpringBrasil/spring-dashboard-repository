package com.springbrasil.repository.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springbrasil.repository.model.Repository;

public interface RepositoryDao extends MongoRepository<Repository, String>{

}
