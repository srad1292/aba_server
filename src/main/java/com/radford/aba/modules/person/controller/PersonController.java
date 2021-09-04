/**
 * 
 */
package com.radford.aba.modules.person.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.radford.aba.modules.person.entity.Person;
import com.radford.aba.modules.person.repository.PersonRepository;

import com.radford.aba.shared.exception.ABAErrorMessage;


/**
 * @author smr12
 *
 */
@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@PostMapping("/person")
	public ResponseEntity<?> createPerson(@Valid @RequestBody Person person) {
		if(person.getId() != null) {
			person.setId(null);
		}
		Person createdPerson = personRepository.save(person);
		return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
	}
	
	@GetMapping("/person/{personId}") 
	public ResponseEntity<?> getPersonById(@PathVariable Integer personId){
		Optional<Person> personIfExists = personRepository.findById(personId);
		if(personIfExists.isPresent()) {
			Person found = personIfExists.get();
			return new ResponseEntity<>(found, HttpStatus.OK);
		}
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'Person' found with ID: " + personId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
}
