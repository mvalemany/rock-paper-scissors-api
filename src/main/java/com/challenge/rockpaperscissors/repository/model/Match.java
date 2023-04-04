package com.challenge.rockpaperscissors.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Match entity
 *
 * @author mvalls
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MATCH")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "player_one", nullable = false)
	private Integer playerOne;

	@Column(name = "player_two", nullable = false)
	private Integer playerTwo;

	@Column(name = "weapon_one", nullable = false)
	private Integer weaponOne;

	@Column(name = "weapon_two", nullable = false)
	private Integer weaponTwo;

	@Column(name = "result", nullable = false)
	private Integer result;

}
