package com.springbrasil.repository.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springbrasil.repository.controller.RepositoryController;
import com.springbrasil.repository.helper.JsonHelper;
import com.springbrasil.repository.model.Repository;
import com.springbrasil.repository.model.RepositoryType;
import com.springbrasil.repository.service.RepositoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(RepositoryController.class)
public class RepositoryControllerUnitTest {
	
	@MockBean
	private RepositoryService repositoryService;
	
	@Autowired
    private MockMvc mvc;
	
	private Repository repository;
	
	@Before
	public void setUp() {
		repository = new Repository();
		repository.setId("6e71d0d568e134c029203593b00a0103e7cdf30b");
		repository.setUrl("http://my_repository");
		repository.setType(RepositoryType.GITHUB);
		doReturn(repository).when(repositoryService).save(any(Repository.class));
	}
	
	@Test
	public void shouldCreateRepository() throws Exception {
		String request = JsonHelper.getRequestFileAsString("repository/create_repository_success.json");
		String response = JsonHelper.getResponseFileAsString("repository/create_repository_success.json");
		
		mvc.perform(post("/repositories")
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.content(request)
			.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
			)
	        .andExpect(status().isCreated()).andExpect(content().json(response));
		
		verify(repositoryService, times(1)).save(any(Repository.class));
	}

}
