package com.example.assignment.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.assignment.entity.Heroe;
import com.assignment.repository.HeroesRepository;
import com.assignment.services.HeroesService;

@ExtendWith(MockitoExtension.class)
class HeroesServicesTest {

	private HeroesRepository heroesRepository;
	private HeroesService heroesService;
	private Heroe expectedHeroe1;
	private Heroe expectedHeroe2;
	
	@BeforeEach
	public void init(@Mock HeroesRepository heroesRepository) {
		this.heroesRepository = heroesRepository;
		this.heroesService = new HeroesService(this.heroesRepository);
		this.expectedHeroe1 = new Heroe("firstName1", "lastName1","nickName1");
		this.expectedHeroe2 = new Heroe("firstName2", "lastName2","nickName2");
	}
		
	@Test
	void testGetHeroeById() {
		Long id = 1L;
		when(this.heroesRepository.findById(id)).thenReturn(Optional.of(expectedHeroe1));
		
		Heroe actualHeroe = this.heroesService.getHeroeById(id).get();
		
		assertEquals(expectedHeroe1,actualHeroe);
		verify(this.heroesRepository,times(1)).findById(id);
	}

	@Test
	void testFindByLastNameLike() {
		String subStringToSearch = "lastName";
		when(this.heroesRepository.findByLastNameLike(subStringToSearch)).thenReturn(List.of(expectedHeroe1,expectedHeroe2));
		
		List<Heroe> actualHeroeList = this.heroesService.findByLastNameLike(subStringToSearch);
		
		assertEquals(expectedHeroe1,actualHeroeList.get(0));
		assertEquals(expectedHeroe2,actualHeroeList.get(1));
		verify(this.heroesRepository,times(1)).findByLastNameLike(subStringToSearch);
	}

	@Test
	void testDelete() {
		Long id = 1L;
		doNothing().when(this.heroesRepository).deleteById(id);
		
		this.heroesService.delete(id);
		
		verify(this.heroesRepository,times(1)).deleteById(id);
	}

	@Test
	void testSave() {
		Heroe expectedSavedHeroe = expectedHeroe1;
		when(this.heroesRepository.save(expectedSavedHeroe)).thenReturn(expectedSavedHeroe);
		
		Heroe actualSavedHeroe = this.heroesService.save(expectedSavedHeroe);
		
		assertEquals(expectedSavedHeroe,actualSavedHeroe);
		
		verify(this.heroesRepository,times(1)).save(expectedSavedHeroe);
	}

	@Test
	void testFindAll() {
		String subStringToSearch = "lastName";
		when(this.heroesRepository.findByLastNameLike(subStringToSearch)).thenReturn(List.of(expectedHeroe1,expectedHeroe2));
		
		List<Heroe> actualHeroeList = this.heroesService.findByLastNameLike(subStringToSearch);
		
		assertEquals(expectedHeroe1,actualHeroeList.get(0));
		assertEquals(expectedHeroe2,actualHeroeList.get(1));
		verify(this.heroesRepository,times(1)).findByLastNameLike(subStringToSearch);
	}

}
