package com.assignment.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.assignment.entity.Heroe;
import com.assignment.services.HeroesService;
import com.assignment.utils.TrackExecutionTime;

@RestController
public class HeroesController {

	HeroesService  heroesService;
	
	public HeroesController(@Autowired HeroesService heroesServices) {
		this.heroesService = heroesServices;		
	}
	
	
	@GetMapping("/heroes/{id}")
	public Optional<Heroe> getHeroe(@PathVariable(name="id") Long id) {
		return heroesService.getHeroeById(id);
	}
	
	@PostMapping("/heroes")
	public ResponseEntity<Object> getHeroe(@RequestBody Heroe heroe) {
		Heroe savedHeroe = heroesService.save(heroe);
		System.out.println("SavedId"+savedHeroe.getId());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedHeroe.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/heroes/{id}")
	public ResponseEntity<Object> putHeroe(@PathVariable(name="id") Long id,@RequestBody Heroe heroe) {
		Optional<Heroe> heroeOptional = heroesService.getHeroeById(id);

		if (heroeOptional.isEmpty())
			return ResponseEntity.notFound().build();

		heroe.setId(id);		
		heroesService.save(heroe);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/heroes/{id}")
	public void deleteHeroe(@PathVariable(name="id") Long id) {
		heroesService.delete(id);
	}
	
	@GetMapping("/heroes")
	public List<Heroe> getHeroe(@RequestParam String lastName) {
		return heroesService.findByLastNameLike("%" + lastName + "%");
	}
	
	@GetMapping("/heroes/all")
	@TrackExecutionTime
	public Iterable<Heroe> getHeroe() {
		return heroesService.findAll();
	}

}
