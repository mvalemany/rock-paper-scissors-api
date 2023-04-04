package com.challenge.rockpaperscissors.dto.score;

import com.challenge.rockpaperscissors.repository.model.Score;
import com.challenge.rockpaperscissors.repository.model.enums.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link Score}
 *
 * @author mvalls
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {

	private Player player;

	private int victories;

	private int matches;

}
