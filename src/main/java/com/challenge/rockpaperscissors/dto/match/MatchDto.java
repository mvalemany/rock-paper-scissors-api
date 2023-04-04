package com.challenge.rockpaperscissors.dto.match;

import com.challenge.rockpaperscissors.repository.model.Match;
import com.challenge.rockpaperscissors.repository.model.enums.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link Match}
 * 
 * @author mvalls
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {

	private MoveResponseDto playerOneMove;
	
	private MoveResponseDto playerTwoMove;
	
	private Result result;
	
	public Integer getWinner() {
		Integer winner = null;
		if (result != null && result.getValue() != null) {
			winner = result.getValue();
		}

		return winner;
	}
	
	public Integer getPlayerOneId() {
		return getPlayerId(playerOneMove);
	}

	public Integer getPlayerTwoId() {
		return getPlayerId(playerTwoMove);
	}

	private Integer getPlayerId(MoveResponseDto playerMove) {
		Integer playerId = null;
		if (playerMove != null && playerMove.getPlayer() != null) {
			playerId = playerMove.getPlayer().getId();
		}

		return playerId;
	}
}
