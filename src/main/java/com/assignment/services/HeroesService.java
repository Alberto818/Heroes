package com.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.assignment.entity.Heroe;
import com.assignment.repository.HeroesRepository;

@Service
public class HeroesService {

	/**
	 * Repository of heroes (Crud Repository)
	 */
	private HeroesRepository heroesRepository;
	
	public HeroesService(@Autowired HeroesRepository heroesRepository) {
		this.heroesRepository = heroesRepository;
	}
	
	@Cacheable(value = "heroes", key = "#id")
	public Optional<Heroe> getHeroeById(Long id) {		
		Optional<Heroe> out = heroesRepository.findById(id);		
		return out;
	}
	
	public List<Heroe> findByLastNameLike(String subString){
		return heroesRepository.findByLastNameLike(subString);
	}
	
	@CacheEvict(value = "heroes", key = "#id")
	public void delete(Long id) {
		heroesRepository.deleteById(id);
	}
	
	@CachePut(value = "heroes", key = "#heroe.id")
	public Heroe save(Heroe heroe) {
		return heroesRepository.save(heroe);
	}
	
	public Iterable<Heroe> findAll(){
		return heroesRepository.findAll();
	}
}
