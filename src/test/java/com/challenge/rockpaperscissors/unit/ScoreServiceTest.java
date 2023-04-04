package com.challenge.rockpaperscissors.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.rockpaperscissors.dto.score.ScoreDto;
import com.challenge.rockpaperscissors.dto.score.ScoreListDto;
import com.challenge.rockpaperscissors.repository.ScoreRepository;
import com.challenge.rockpaperscissors.repository.model.Score;
import com.challenge.rockpaperscissors.service.impl.ScoreServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ScoreServiceTest {

	private static final Integer FIRST_SCORE_PLAYER_ID = 2;
	private static final Integer FIRST_SCORE_VICTORIES = 1;
	private static final Integer FIRST_SCORE_MATCHES = 1;

	private static final Integer SECOND_SCORE_PLAYER_ID = 1;
	private static final Integer SECOND_SCORE_VICTORIES = 0;
	private static final Integer SECOND_SCORE_MATCHES = 1;

	private static final Score FIRST_SCORE = new Score(FIRST_SCORE_PLAYER_ID, FIRST_SCORE_VICTORIES, FIRST_SCORE_MATCHES);
	private static final Score SECOND_SCORE = new Score(SECOND_SCORE_PLAYER_ID, SECOND_SCORE_VICTORIES, SECOND_SCORE_MATCHES);

	@InjectMocks
	ScoreServiceImpl scoreService;

	@Mock
	ScoreRepository scoreRepositoryMock;

	@Test
	void shouldGetEmptyListWhenEmptyTable() {
		when(scoreRepositoryMock.findByOrderByVictoriesDesc()).thenReturn(new ArrayList<>());

		ScoreListDto scoreDtoList = scoreService.getScores();

		assertTrue(scoreDtoList.getScores().isEmpty(), "List must be empty");
	}

	@Test
	void shouldGetScores() {
		List<Score> scoreList = new ArrayList<>();
		scoreList.add(FIRST_SCORE);
		scoreList.add(SECOND_SCORE);
		when(scoreRepositoryMock.findByOrderByVictoriesDesc()).thenReturn(scoreList);

		ScoreListDto scoreDtoList = scoreService.getScores();

		assertEquals(scoreList.size(), scoreDtoList.getScores().size());

		ScoreDto firstScoreDto = scoreDtoList.getScores().get(0);
		assertEquals(FIRST_SCORE.getPlayerId(), firstScoreDto.getPlayer().getId());
		assertEquals(FIRST_SCORE.getVictories(), firstScoreDto.getVictories());
		assertEquals(FIRST_SCORE.getMatches(), firstScoreDto.getMatches());

		ScoreDto secondScoreDto = scoreDtoList.getScores().get(1);
		assertEquals(SECOND_SCORE.getPlayerId(), secondScoreDto.getPlayer().getId());
		assertEquals(SECOND_SCORE.getVictories(), secondScoreDto.getVictories());
		assertEquals(SECOND_SCORE.getMatches(), secondScoreDto.getMatches());
	}

}
