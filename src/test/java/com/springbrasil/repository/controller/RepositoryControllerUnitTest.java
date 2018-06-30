package com.springbrasil.repository.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import javax.ws.rs.NotFoundException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

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
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private Repository repository;
	
	@Before
	public void setUp() {
		repository = new Repository();
		repository.setId("6e71d0d568e134c029203593b00a0103e7cdf30b");
		repository.setUrl("http://my_repository");
		repository.setType(RepositoryType.GITHUB);
		doReturn(repository).when(repositoryService).save(any(Repository.class));
		doReturn(new PageImpl<>(Arrays.asList(repository))).when(repositoryService).getAll(anyInt(), anyInt());
		doReturn(repository).when(repositoryService).get(eq("6e71d0d568e134c029203593b00a0103e7cdf30b"));
		doThrow(new NotFoundException("unexisting_repository")).when(repositoryService).get(eq("unexisting_repository"));
	}

	@Test
	public void shouldCreateRepository() throws Exception {
		String request = JsonHelper.getRequestFileAsString("repository/create_repository_success.json");
		String response = JsonHelper.getResponseFileAsString("repository/create_repository_success.json");

		mvc.perform(post("/repositories").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(request)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isCreated())
				.andExpect(content().json(response));

		verify(repositoryService, times(1)).save(any(Repository.class));
	}

	@Test
	public void shouldGetAllRepositories() throws Exception {
		String response = JsonHelper.getResponseFileAsString("repository/get_all_repositories_success.json");

		mvc.perform(get("/repositories").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().json(response));

		verify(repositoryService, times(1)).getAll(0, 10);
	}
	
	@Test
	public void shouldGetOneRepository() throws Exception {
		String response = JsonHelper.getResponseFileAsString("repository/get_one_repository_success.json");

		mvc.perform(get("/repositories/6e71d0d568e134c029203593b00a0103e7cdf30b").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().json(response));

		verify(repositoryService, times(1)).get("6e71d0d568e134c029203593b00a0103e7cdf30b");
	}
	
	@Test
	public void shouldThrowErrorWhenGetUnexistingRepository() throws Exception {
		exception.expect(NestedServletException.class);
		exception.expectCause(instanceOf(NotFoundException.class));
		
		mvc.perform(get("/repositories/unexisting_repository")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound());
	}

}
