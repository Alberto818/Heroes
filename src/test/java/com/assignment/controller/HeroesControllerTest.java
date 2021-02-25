package com.assignment.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.assignment.App;
import com.assignment.entity.Heroe;

@SpringBootTest(classes = App.class,webEnvironment = WebEnvironment.RANDOM_PORT)
class HeroesControllerTest {
	
	@Autowired 
	RestTemplate restTemplate;


    @LocalServerPort
    private int port;
		
	private String getRootUrl() {
        return "http://localhost:" + port;
    }
	
	/**
	 * Get Superman heroe by using the id 1
	 */
	@Test
	void testGetHeroeById() {
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Heroe> entity = new HttpEntity<Heroe>(null, headers);
        ResponseEntity<Heroe> response = restTemplate.exchange(getRootUrl() + "/heroes/1",
        HttpMethod.GET, entity, Heroe.class);
        Heroe actualResponse = response.getBody();
        
        assertNotNull(actualResponse);
        assertEquals(1L,actualResponse.getId());
        assertEquals("Clack",actualResponse.getFirstName());
        assertEquals("Kent",actualResponse.getLastName());
        assertEquals("Superman",actualResponse.getNickName());
        
	}

	/**
	 * Update the heroe with id 2.
	 */
	@Test
	void testUpdateHeroeWithId2() {
		
		Heroe heroeToSave = new Heroe("Juan", "Lopez","SuperLopez");
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Heroe> entity = new HttpEntity<Heroe>(heroeToSave, headers);
        ResponseEntity<Heroe> response = restTemplate.exchange(getRootUrl() + "/heroes/2",
        HttpMethod.PUT, entity, Heroe.class);
        HttpStatus status = response.getStatusCode();
        
        assertNotNull(status);
        assertEquals(HttpStatus.NO_CONTENT,status);        
        
	}

	/**
	 * Delete Heroe with Id 3.
	 */
	@Test
	void testDeleteHeroeWithId3() {
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Heroe> entity = new HttpEntity<Heroe>(null, headers);
        ResponseEntity<Heroe> response = restTemplate.exchange(getRootUrl() + "/heroes/3",
        HttpMethod.DELETE, entity, Heroe.class);
        HttpStatus status = response.getStatusCode();
        
        assertNotNull(status);
        assertEquals(HttpStatus.OK,status); 
	}
	
	/**
	 * Create a new heroe called QuickSilver.
	 */
	@Test
	void testPostHeroe() {
		
		Heroe heroeToSave = new Heroe("Pietro","Maximoff","Quicksilver");
		heroeToSave.setId(4L);
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Heroe> entity = new HttpEntity<Heroe>(heroeToSave, headers);
        ResponseEntity<Heroe> response = restTemplate.exchange(getRootUrl() + "/heroes",
        HttpMethod.POST, entity, Heroe.class);
        HttpStatus status = response.getStatusCode();
        
        assertNotNull(status);
        assertEquals(HttpStatus.CREATED,status);        
        
	}
	
	/**
	 * Check the general error management.
	 */
	@Test
	void testDeleteHeroeWithFail() {
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Heroe> entity = new HttpEntity<Heroe>(null, headers);
		try {
        restTemplate.exchange(getRootUrl() + "/heroes/5",HttpMethod.DELETE, entity, Object.class);
        }catch(HttpServerErrorException.InternalServerError error) {        	
            HttpStatus status = error.getStatusCode();
            
            assertNotNull(status);
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,status);            
            assertEquals("{\"internalCode\":500,\"internalMsg\":\"Api general error. Please wait and try again.\"}",error.getResponseBodyAsString());
	
        }
   	}

}
