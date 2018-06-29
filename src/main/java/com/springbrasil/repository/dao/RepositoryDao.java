package com.springbrasil.repository.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springbrasil.repository.model.Repository;

public interface RepositoryDao extends PagingAndSortingRepository<Repository, String>{

}
