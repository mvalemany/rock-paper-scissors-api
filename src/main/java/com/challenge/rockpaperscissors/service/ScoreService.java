package com.challenge.rockpaperscissors.service;

import com.challenge.rockpaperscissors.dto.match.MatchDto;
import com.challenge.rockpaperscissors.dto.score.ScoreListDto;

/**
 * Manages the scores
 *
 * @author mvalls
 */
public interface ScoreService {

	/*
	 * Retrieves all the scores
	 *
	 * @return {@link ScoreListDto}
	 */
	public ScoreListDto getScores();

	/**
	 * Saves the results of a single match
	 *
	 * @param matchDto {@link MatchDto}
	 */
	public void saveScores(MatchDto matchDto);

}
