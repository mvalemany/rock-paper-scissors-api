package com.challenge.rockpaperscissors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.rockpaperscissors.dto.match.MatchDto;
import com.challenge.rockpaperscissors.dto.match.MoveDto;
import com.challenge.rockpaperscissors.service.MatchService;

/**
 * Match API
 *
 * @author mvalls
 */
@RestController
@RequestMapping(value = "/match")
public class MatchController {

	@Autowired
	private MatchService matchService;

	/**
	 * Creates a match against the machine given a player's move
	 *
	 * @param humanMove
	 * @return {@link MatchDto}
	 */
	@PostMapping( value = "/playMatch",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<MatchDto> playMatch(@RequestBody MoveDto humanMove) {
		return new ResponseEntity<>(
				matchService.playMatch(humanMove),
				HttpStatus.OK);
	}
}
