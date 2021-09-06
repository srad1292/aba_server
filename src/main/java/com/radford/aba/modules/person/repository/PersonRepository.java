package com.radford.aba.modules.person.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.radford.aba.modules.person.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	Page<Person> findAll(Pageable page);
	
	Page<Person> findAllByFullNameContainsIgnoreCase(Pageable page, String fullName);

}
