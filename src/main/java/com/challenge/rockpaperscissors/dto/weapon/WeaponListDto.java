package com.challenge.rockpaperscissors.dto.weapon;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Output DTO containing a list of {@link WeaponDto}
 *
 * @author mvalls
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponListDto {

	private List<WeaponDto> weapons;

}
