package com.challenge.rockpaperscissors.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Score entity
 *
 * @author mvalls
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SCORE")
public class Score {

	@Id
	@Column(name = "player_id", nullable = false)
	private Integer playerId;

	@Column(name = "victories", nullable = false)
	private Integer victories;

	@Column(name = "matches", nullable = false)
	private Integer matches;

}
