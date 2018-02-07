package com.springbrasil.repository.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springbrasil.repository.model.Repository;
import com.springbrasil.repository.model.RepositoryType;
import com.springbrasil.repository.service.RepositoryService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryServiceIntegrationTest {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Test
	public void shouldSaveRepository() {
		Repository repository = new Repository();
		repository.setUrl("http://my_repository");
		repository.setType(RepositoryType.GITHUB);
		Repository savedRepository = repositoryService.save(repository);
		
		assertThat(savedRepository.getId()).isNotNull();
		
	}

}
