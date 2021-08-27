package com.radford.aba.modules.award.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.radford.aba.modules.award.entity.Award;
import com.radford.aba.modules.award.respository.AwardRepository;


@RestController
public class AwardController {

	@Autowired
	private AwardRepository awardRepository;
	
	@GetMapping("/award")
	public List<Award> getAllAwards() {	
		List<Award> awards = new ArrayList<>();
		awardRepository.findAll().forEach(awards::add);
		return awards;
	}
	
	@PostMapping("/award")
	public ResponseEntity<?> createAward(@Valid @RequestBody Award award) {
		if(award.getId() != null) {
			award.setId(null);
		}
		System.out.println("I am inside create award");
		Award createdAward = awardRepository.save(award);
		return new ResponseEntity<>(createdAward, HttpStatus.CREATED);
		
	}
}
