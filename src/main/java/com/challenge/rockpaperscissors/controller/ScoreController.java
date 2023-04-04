package com.challenge.rockpaperscissors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.rockpaperscissors.dto.score.ScoreListDto;
import com.challenge.rockpaperscissors.service.ScoreService;

/**
 * Score API
 * 
 * @author mvalls
 */
@RestController
@RequestMapping(value = "/score")
public class ScoreController {
	
	@Autowired
	private ScoreService scoresService;

	/*
	 * Retrieves all the scores
	 * 
	 * @return {@link ScoreListDto}
	 */
	@GetMapping( value = "/getScores", 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ScoreListDto> getScores() {
		return new ResponseEntity<>(
				scoresService.getScores(),
				HttpStatus.OK);
	}
}
