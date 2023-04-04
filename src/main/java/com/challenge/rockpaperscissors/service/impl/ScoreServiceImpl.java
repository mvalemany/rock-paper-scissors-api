package com.challenge.rockpaperscissors.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.rockpaperscissors.dto.match.MatchDto;
import com.challenge.rockpaperscissors.dto.score.ScoreDto;
import com.challenge.rockpaperscissors.dto.score.ScoreListDto;
import com.challenge.rockpaperscissors.repository.ScoreRepository;
import com.challenge.rockpaperscissors.repository.model.Score;
import com.challenge.rockpaperscissors.repository.model.enums.Player;
import com.challenge.rockpaperscissors.service.ScoreService;

/**
 * Implements {@link ScoreService}
 *
 * @author mvalls
 */
@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	ScoreRepository scoreRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreListDto getScores() {
		List<Score> scores = scoreRepository.findByOrderByVictoriesDesc();

		return new ScoreListDto(mapToDtoList(scores));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveScores(MatchDto matchDto) {

		Integer playerOneId = matchDto.getPlayerOneId();
		Integer playerTwoId = matchDto.getPlayerTwoId();
		Integer winner = matchDto.getWinner();

		List<Integer> playerIds = Arrays.asList(playerOneId, playerTwoId);

		Map<Integer, Score> scoresMap = scoreRepository.findByPlayerIdIn(playerIds)
				.stream()
				.collect(Collectors.toMap(
						Score::getPlayerId,
						score -> score));

		Score playerOneScore = getScoreToSave(
				playerOneId,
				winner,
				scoresMap.get(playerOneId));

		Score playerTwoScore = getScoreToSave(
				playerTwoId,
				winner,
				scoresMap.get(playerTwoId));

		scoreRepository.saveAll(Arrays.asList(playerOneScore, playerTwoScore));
	}

	private Score getScoreToSave(Integer playerId, Integer winner, Score playerScore) {
		if (playerScore == null) {
			playerScore = new Score(
					playerId,
					playerId.equals(winner) ? 1 : 0,
							1);
		} else {
			playerScore.setMatches(playerScore.getMatches() + 1);
			Integer playerVictories = playerScore.getVictories();
			playerScore.setVictories(playerId.equals(winner) ? playerVictories + 1 : playerVictories);
		}
		return playerScore;
	}

	private ScoreDto mapToDto(Score score) {
		return new ScoreDto(
				Player.getById(score.getPlayerId()),
				score.getVictories(),
				score.getMatches());
	}

	private List<ScoreDto> mapToDtoList(List<Score> scores) {
		return scores.stream()
				.map(this::mapToDto)
				.toList();
	}
}
