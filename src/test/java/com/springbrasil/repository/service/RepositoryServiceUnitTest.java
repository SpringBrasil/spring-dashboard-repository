package com.springbrasil.repository.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.ws.rs.NotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springbrasil.repository.dao.RepositoryDao;
import com.springbrasil.repository.model.Repository;
import com.springbrasil.repository.service.RepositoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryServiceUnitTest {
	
	@MockBean
	private RepositoryDao repositoryDao;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Test
	public void shouldSaveRepository() {
		repositoryService.save(new Repository());
		
		verify(repositoryDao, times(1)).save(any(Repository.class));
	}
	
	@Test
	public void shouldFindAllRepositories() {
		repositoryService.getAll(0, 10);
		
		verify(repositoryDao, times(1)).findAll(any(PageRequest.class));
	}
	
	@Test
	public void shouldFindOneRepository() {
		doReturn(new Repository()).when(repositoryDao).findOne(anyString());
		repositoryService.get("123");
		
		verify(repositoryDao, times(1)).findOne(anyString());
	}
	
	@Test(expected=NotFoundException.class)
	public void shouldThrowErrorWhenDoesntFindOneRepository() {
		doReturn(null).when(repositoryDao).findOne(eq("unexisting_repository"));
		repositoryService.get("unexisting_repository");
	}

}
