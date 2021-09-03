/**
 * 
 */
package com.radford.aba.modules.team.controller;

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

import com.radford.aba.modules.team.entity.Team;
import com.radford.aba.modules.team.repository.TeamRepository;
import com.radford.aba.shared.exception.ABAErrorMessage;

/**
 * @author smr12
 *
 */
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@GetMapping("/team-first")
	public List<Team> getAllTeam() {
		List<Team> firsts = new ArrayList<>();
		teamRepository.findAll().forEach(firsts::add);
		return firsts;
	}
	
	@PostMapping("/team-first")
	public ResponseEntity<?> createTeam(@Valid @RequestBody Team team) {
		if(team.getId() != null) {
			team.setId(null);
		}
		Team createdEvent = teamRepository.save(team);
		return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
	}
	
	@PutMapping("/team-first/{teamId}")
	public ResponseEntity<?> updateTeam(@PathVariable Integer teamId, @Valid @RequestBody Team team) {
		if(teamRepository.existsById(teamId)) {
			team.setId(teamId);
			Team savedEvent = teamRepository.save(team);
			return new ResponseEntity<>(savedEvent, HttpStatus.OK);
		}
		
		
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'team' found with ID: " + teamId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/team-first/{teamId}")
	public ResponseEntity<?> deleteTeam(@PathVariable Integer teamId) {
		if(teamRepository.existsById(teamId)) {
			teamRepository.deleteById(teamId);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		
		
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'team' found with ID: " + teamId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
}
