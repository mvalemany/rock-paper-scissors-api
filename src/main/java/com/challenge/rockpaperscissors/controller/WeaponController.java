package com.challenge.rockpaperscissors.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.rockpaperscissors.dto.weapon.WeaponDto;
import com.challenge.rockpaperscissors.dto.weapon.WeaponListDto;

/**
 * Weapon API
 * 
 * @author mvalls
 */
@RestController
@RequestMapping(value = "/weapon")
public class WeaponController {

	@Autowired
	Map<Integer, WeaponDto> weapons;

	/*
	 * Retrieves all the weapons
	 * 
	 * @return {@link WeaponListDto}
	 */
	@GetMapping( value = "/getList",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<WeaponListDto> getWeapons() {
		return new ResponseEntity<>(
				new WeaponListDto(new ArrayList<>(weapons.values())),
				HttpStatus.OK);
	}
}
