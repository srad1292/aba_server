/**
 * 
 */
package com.radford.aba.modules.event_first.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.radford.aba.modules.event_first.entity.EventFirst;
import com.radford.aba.modules.event_first.repository.EventFirstRepository;
import com.radford.aba.shared.exception.ABAErrorMessage;

/**
 * @author smr12
 *
 */
@RestController
public class EntityFirstController {

	@Autowired
	private EventFirstRepository eventFirstRepository;
	
	@GetMapping("/event-first")
	public List<EventFirst> getAllEventFirst() {
		List<EventFirst> firsts = new ArrayList<>();
		eventFirstRepository.findAll().forEach(firsts::add);
		return firsts;
	}
	
	@PostMapping("/event-first")
	public ResponseEntity<?> createEventFirst(@Valid @RequestBody EventFirst eventFirst) {
		if(eventFirst.getId() != null) {
			eventFirst.setId(null);
		}
		EventFirst createdEvent = eventFirstRepository.save(eventFirst);
		return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
	}
	
	@PutMapping("/event-first/{eventId}")
	public ResponseEntity<?> updateEventFirst(@PathVariable Integer eventId, @Valid @RequestBody EventFirst eventFirst) {
		if(eventFirstRepository.existsById(eventId)) {
			eventFirst.setId(eventId);
			EventFirst savedEvent = eventFirstRepository.save(eventFirst);
			return new ResponseEntity<>(savedEvent, HttpStatus.OK);
		}
		
		
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'event first' found with ID: " + eventId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/event-first/{eventId}")
	public ResponseEntity<?> deleteEventFirst(@PathVariable Integer eventId) {
		if(eventFirstRepository.existsById(eventId)) {
			eventFirstRepository.deleteById(eventId);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		
		
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'event first' found with ID: " + eventId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
}
