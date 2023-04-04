package com.challenge.rockpaperscissors.dto.weapon;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO with weapons data
 *
 * @author mvalls
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponDto {

	private Integer id;

	private String name;

	private Set<Integer> strengths;

}
