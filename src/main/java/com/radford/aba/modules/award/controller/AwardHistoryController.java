package com.radford.aba.modules.award.controller;

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

import com.radford.aba.modules.award.entity.AwardHistory;
import com.radford.aba.modules.award.respository.AwardHistoryRepository;
import com.radford.aba.shared.exception.ABAErrorMessage;

@RestController
public class AwardHistoryController {

	@Autowired
	private AwardHistoryRepository awardHistoryRepository;	
	
	@PostMapping("/award-history")
	public ResponseEntity<?> createAwardHistory(@Valid @RequestBody AwardHistory awardHistory) {
		if(awardHistory.getId() != null) {
			awardHistory.setId(null);
		}
		
		AwardHistory createdAwardHistory = awardHistoryRepository.save(awardHistory);
		return new ResponseEntity<>(createdAwardHistory, HttpStatus.CREATED);
	}
	
	@GetMapping("/award-history")
	public Page<AwardHistory> getAllAwardHistory(
			@RequestParam(defaultValue="1") Integer pageNumber,
			@RequestParam(defaultValue="5") Integer pageSize,
			@RequestParam(defaultValue="id") String sortBy,
			@RequestParam(defaultValue="ASC") String sortDirection
	) {
		pageNumber--;
		Sort.Direction direction = sortDirection.toLowerCase().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));
		return awardHistoryRepository.findAll(page);
	}
	
	@GetMapping("/award-history/{awardHistoryId}")
	public ResponseEntity<?> getAwardHistoryById(@PathVariable Integer awardHistoryId) {
		Optional<AwardHistory> awardHistoryIfExists = awardHistoryRepository.findById(awardHistoryId);
		if(awardHistoryIfExists.isPresent()) {
			AwardHistory found = awardHistoryIfExists.get();
			return new ResponseEntity<>(found, HttpStatus.OK);
		}
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'AwardHistory' found with ID: " + awardHistoryId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/award-history/{awardHistoryId}")
	public ResponseEntity<?> updateAwardHistory(@PathVariable Integer awardHistoryId, @Valid @RequestBody AwardHistory awardHistory) {
		if(awardHistoryRepository.existsById(awardHistoryId)) {
			awardHistory.setId(awardHistoryId);
			AwardHistory savedAwardHistory = awardHistoryRepository.save(awardHistory);
			return new ResponseEntity<>(savedAwardHistory, HttpStatus.OK);
		}
		
		ABAErrorMessage errorMessage = new ABAErrorMessage("No 'Award History' found with ID: " + awardHistoryId);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
}
