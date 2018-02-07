package com.springbrasil.repository.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.springbrasil.repository.controller.response.RepositoryCreateResponse;
import com.springbrasil.repository.helper.JsonHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RepositoryControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldCreateRepository() {
		String request = JsonHelper.getRequestFileAsString("repository/create_repository_success.json");
		
		HttpHeaders headers = getHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(request, headers);
		
		ResponseEntity<String> response = testRestTemplate.exchange("/repositories", HttpMethod.POST, requestEntity, String.class);
		JsonHelper.assertKeysLength(response.getBody(), 1);
		
		RepositoryCreateResponse createResponse = JsonHelper.parse(response.getBody(), RepositoryCreateResponse.class);
		assertThat(createResponse.getId()).isNotNull();
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		return headers;
	}
	
}
