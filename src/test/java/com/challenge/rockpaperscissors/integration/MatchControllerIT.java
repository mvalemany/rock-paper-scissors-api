package com.challenge.rockpaperscissors.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.challenge.rockpaperscissors.dto.match.MatchDto;
import com.challenge.rockpaperscissors.dto.match.MoveDto;
import com.challenge.rockpaperscissors.dto.score.ScoreDto;
import com.challenge.rockpaperscissors.dto.score.ScoreListDto;
import com.challenge.rockpaperscissors.integration.config.BaseIT;
import com.challenge.rockpaperscissors.repository.model.enums.Player;
import com.challenge.rockpaperscissors.repository.model.enums.Result;

public class MatchControllerIT extends BaseIT {
	
	private static final int TOTAL_MATCHES = 1;
	private static final int TOTAL_VICTORIES_WINNER = 1;
	private static final int TOTAL_VICTORIES_LOSER = 0;
	
	private static final Integer HUMAN_WEAPON_ID = 1;

    private String playMatchUri = "/match/playMatch";
    private String scoreGetListUri = "/score/getScores";

    @Test
    void shouldUpdateScoresWhenPlayMatch() {
    	
		HttpEntity<MoveDto> humanMove = new HttpEntity<>( new MoveDto(HUMAN_WEAPON_ID));
    	
        String url = getUrl(playMatchUri);
		ResponseEntity<MatchDto> matchResponse = restTemplate.exchange(
        		url,
        		HttpMethod.POST,
        		humanMove,
        		MatchDto.class);

        assertEquals(HttpStatus.OK, matchResponse.getStatusCode(), "Code status must be 200");

        MatchDto body = matchResponse.getBody();
		assertEquals(MatchDto.class, body.getClass(), "Body object must be a MatchDto");
		
		assertNotNull(body.getPlayerOneMove(), "Player one move cannot be null");
		assertNotNull(body.getPlayerOneMove().getPlayer(), "Player one cannot be null");
		assertEquals(Player.MACHINE.getId(), body.getPlayerOneId(), "Player one id cannot be null");
		assertNotNull(body.getPlayerOneMove().getWeapon(), "Player one weapon cannot be null");
		assertNotNull(body.getPlayerOneMove().getWeapon().getId(), "Player one weapon id cannot be null");
		
		assertNotNull(body.getPlayerTwoMove(), "Player two move cannot be null");
		assertNotNull(body.getPlayerTwoMove().getPlayer(), "Player two cannot be null");
		assertEquals(Player.HUMAN.getId(), body.getPlayerTwoId(), "Player two id cannot be null");
		assertNotNull(body.getPlayerTwoMove().getWeapon(), "Player two weapon cannot be null");
		assertEquals(HUMAN_WEAPON_ID, body.getPlayerTwoMove().getWeapon().getId(), "Player two weapon id must be equal to input move");

		Result result = body.getResult();
		assertNotNull(result, "Result cannot be null");
		
		ResponseEntity<ScoreListDto> scoresResponse = restTemplate.getForEntity(
        		getUrl(scoreGetListUri),
        		ScoreListDto.class);
		
		assertEquals(HttpStatus.OK, scoresResponse.getStatusCode(), "Code status must be 200");
		
		ScoreListDto scoresBody = scoresResponse.getBody();
		assertEquals(ScoreListDto.class, scoresBody.getClass(), "Body object must be a ScoreListDto");
		List<ScoreDto> scores = scoresBody.getScores();
		
		ScoreDto machineScore = scores.stream().filter(score -> score.getPlayer().getId() == Player.MACHINE.getId()).findAny().orElse(null);
		
		assertNotNull(machineScore);
		assertEquals(TOTAL_MATCHES, machineScore.getMatches(), "Machine score must have one more match");
		assertEquals(getExpectedVictories(result, Player.MACHINE), 
				machineScore.getVictories(), "Machine's number of victories must be correct");
		
		ScoreDto humanScore = scores.stream().filter(score -> score.getPlayer().getId() == Player.HUMAN.getId()).findAny().orElse(null);
		
		assertNotNull(humanScore);
		assertEquals(TOTAL_MATCHES, humanScore.getMatches(), "Human score must have one more match");
		assertEquals(getExpectedVictories(result, Player.HUMAN),
				humanScore.getVictories(), "Human's number of victories must be correct");
		
    }

	private int getExpectedVictories(Result result, Player player) {
		int i = result.getValue() == player.getId() ? TOTAL_VICTORIES_WINNER : TOTAL_VICTORIES_LOSER;
		return i;
	}

}
