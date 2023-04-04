package com.challenge.rockpaperscissors.dto.match;

import com.challenge.rockpaperscissors.dto.weapon.WeaponDto;
import com.challenge.rockpaperscissors.repository.model.enums.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Output DTO containing player's move info
 *
 * @author mvalls
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveResponseDto {

	private Player player;

	private WeaponDto weapon;

}
