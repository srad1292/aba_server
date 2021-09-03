/**
 * 
 */
package com.radford.aba.modules.team.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.radford.aba.modules.team.entity.Team;
import com.radford.aba.modules.team.repository.TeamRepository;
import com.radford.aba.shared.exception.ABAErrorMessage;

/**
 * @author smr12
 *
 */
@RestController
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@GetMapping("/team")
	public List<Team> getAllTeam() {
		List<Team> teams = new ArrayList<>();
		teamRepository.findAll().forEach(teams::add);
		return teams;
	}
	
	@GetMapping("/team/{teamId}")
	public ResponseEntity<?> getTeamById(@PathVariable Integer teamId) {
		Optional<Team> teamIfExists = teamRepository.findById(teamId);
		if(teamIfExists.isPresent()) {
			Team found = teamIfExists.get();
			return new ResponseEntity<>(found, HttpStatus.OK);
		}
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'Team' found with ID: " + teamId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/team")
	public ResponseEntity<?> createTeam(@Valid @RequestBody Team team) {
		if(team.getId() != null) {
			team.setId(null);
		}
		Team createdEvent = teamRepository.save(team);
		return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
	}
	
	@PutMapping("/team/{teamId}")
	public ResponseEntity<?> updateTeam(@PathVariable Integer teamId, @Valid @RequestBody Team team) {
		if(teamRepository.existsById(teamId)) {
			team.setId(teamId);
			Team savedTeam = teamRepository.save(team);
			return new ResponseEntity<>(savedTeam, HttpStatus.OK);
		}
		
		
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'team' found with ID: " + teamId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
}
