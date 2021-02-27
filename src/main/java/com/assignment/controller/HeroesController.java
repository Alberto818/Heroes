package com.assignment.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.assignment.entity.Heroe;
import com.assignment.services.HeroesService;
import com.assignment.utils.TrackExecutionTime;


@RestController
public class HeroesController implements IHeroesController{

	private HeroesService  heroesService;
	
	public HeroesController(@Autowired HeroesService heroesServices) {
		this.heroesService = heroesServices;		
	}
	

	@Override
	public ResponseEntity<Optional<Heroe>> getHeroe(@PathVariable(name="id") Long id) {
		return ResponseEntity.ok(heroesService.getHeroeById(id));
	} 
	
	@Override
	public ResponseEntity<Object> createHeroe(@RequestBody Heroe heroe) {
		Heroe savedHeroe = heroesService.save(heroe);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedHeroe.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@Override
	public ResponseEntity<Object> updateHeroe(@PathVariable(name="id") Long id,@RequestBody Heroe heroe) {
		Optional<Heroe> heroeOptional = heroesService.getHeroeById(id);

		if (heroeOptional.isEmpty())
			return ResponseEntity.notFound().build();

		heroe.setId(id);		
		heroesService.save(heroe);

		return ResponseEntity.noContent().build();
	}
	
	@Override
	public void deleteHeroe(@PathVariable(name="id") Long id) {
		heroesService.delete(id);
	}
	
	@Override
	public ResponseEntity<List<Heroe>> getHeroeByNickNameLike(@RequestParam String nickNameSubString) {
		return ResponseEntity.ok(heroesService.findByNickNameLike("%" + nickNameSubString + "%"));
	}
	
	@Override
	@TrackExecutionTime
	public ResponseEntity<Iterable<Heroe>> getAllHeroe() {
		return ResponseEntity.ok(heroesService.findAll());
	}

}
