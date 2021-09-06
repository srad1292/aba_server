/**
 * 
 */
package com.radford.aba.modules.person.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/person") 
	public Page<Person> getAllPerson(
			@RequestParam(defaultValue="1") Integer pageNumber,
			@RequestParam(defaultValue="5") Integer pageSize,
			@RequestParam(defaultValue="id") String sortBy,
			@RequestParam(defaultValue="ASC") String sortDirection,
			@RequestParam(required=false) String playerName
	) {
		pageNumber--;
		Sort.Direction direction = sortDirection.toLowerCase().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));
		if(playerName == null) {
			return personRepository.findAll(page);
		} else {
			return personRepository.findAllByFullNameContainsIgnoreCase(page, playerName);
		}
		
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
	
	@PutMapping("person/{personId}")
	public ResponseEntity<?> updatePerson(@PathVariable Integer personId, @Valid @RequestBody Person person) {
		if(personRepository.existsById(personId)) {
			person.setId(personId);
			Person savedPerson = personRepository.save(person);
			return new ResponseEntity<>(savedPerson, HttpStatus.OK);
		}
		
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'Person' found with ID: " + personId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		
	}
}
