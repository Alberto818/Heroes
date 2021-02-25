package com.assignment.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.assignment.entity.Heroe;

public interface HeroesRepository extends CrudRepository<Heroe,Long> {

	List<Heroe> findByNickNameLike(String nickName);
	Iterable<Heroe> findAll();
	
}
