package com.challenge.rockpaperscissors.service;

import com.challenge.rockpaperscissors.dto.match.MatchDto;
import com.challenge.rockpaperscissors.dto.match.MoveDto;

/**
 * Handles the process of playing a match
 *
 * @author mvalls
 */
public interface MatchService {

	/**
	 * Creates a match against the machine given a player's move
	 *
	 * @param humanMove
	 * @return {@link MatchDto}
	 */
	public MatchDto playMatch(MoveDto playerMove);

}
