package com.radford.aba.modules.person.repository;

import org.springframework.data.repository.CrudRepository;

import com.radford.aba.modules.person.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
