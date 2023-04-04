package com.challenge.rockpaperscissors.dto.score;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Output DTO containing a list of {@link ScoreDto}
 *
 * @author mvalls
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreListDto {

	List<ScoreDto> scores;

}
