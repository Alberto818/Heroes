package com.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.entity.Heroe;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="/heroes")
@RestController
public interface IHeroesController {


	@ApiOperation(value="Find a heroe by id")
	@GetMapping("/heroes/{id}")
	public ResponseEntity<Optional<Heroe>> getHeroe(@PathVariable(name="id") Long id);
	
	@ApiOperation(value="Create a new heroe")
	@PostMapping("/heroes")
	public ResponseEntity<Object> createHeroe(@RequestBody Heroe heroe);
	
	@ApiOperation(value="Update a heroe by id")
	@PutMapping("/heroes/{id}")
	public ResponseEntity<Object> updateHeroe(@PathVariable(name="id") Long id,@RequestBody Heroe heroe) ;
	
	@ApiOperation(value="Delete a heroe by id")
	@DeleteMapping("/heroes/{id}")
	public void deleteHeroe(@PathVariable(name="id") Long id);
	
	@ApiOperation(value="Get a heroe by a substring cointained in its nickname")
	@GetMapping("/heroes")
	public ResponseEntity<List<Heroe>> getHeroeByNickNameLike(@RequestParam String nickNameSubString);
	
	@ApiOperation(value="Get all heroes", notes="This method requires authetication with ROLE_AUDITOR")
	@ApiResponses(value= {
			@ApiResponse(code=401, message="Unathorized"),
			@ApiResponse(code=403, message="Forbidden")
			
		}
	)
	@GetMapping("/heroes/all")	
	public ResponseEntity<Iterable<Heroe>> getAllHeroe();

}
