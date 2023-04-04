package com.challenge.rockpaperscissors.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.challenge.rockpaperscissors.dto.weapon.WeaponDto;
import com.challenge.rockpaperscissors.service.MachineService;
import com.challenge.rockpaperscissors.service.impl.MachineServiceImpl;

/**
 * Configures application beans
 *
 * @author mvalls
 */
@Configuration
public class AppConfiguration {

	/**
	 * URL used by the client to access the API services
	 */
	@Value("${client.url}")
	String clientUrl;

	/**
	 * Allows CORS for {@link #clientUrl}
	 */
	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
				.addMapping("/**")
				.allowedOrigins(clientUrl);
			}
		};
	}

	/**
	 * Service implementation to generate the machine's choice
	 *
	 * @param weaponProperties
	 */
	@Bean
	MachineService machineService(WeaponProperties weaponProperties) {
		return new MachineServiceImpl(
				new ArrayList<>(weaponProperties.getTypes().keySet()),
				new Random());
	}

	/**
	 * Map of weapons
	 *
	 * @param weaponProperties
	 */
	@Bean
	Map<Integer, WeaponDto> weapons(WeaponProperties weaponProperties) {

		Map<Integer, String> weaponTypes = weaponProperties.getTypes();
		Map<Integer, Set<Integer>> weaponStrengths = weaponProperties.getStrengths();

		validateWeaponsConfig(weaponTypes, weaponStrengths);

		Map<Integer, WeaponDto> weapons = new HashMap<>();
		for (Entry<Integer, String> weaponType : weaponTypes.entrySet()) {
			addWeaponDto(weaponType, weaponStrengths, weapons);
		}

		return weapons;
	}

	private void addWeaponDto(Entry<Integer, String> weaponType,
			Map<Integer, Set<Integer>> weaponStrengths, Map<Integer, WeaponDto> weapons) {
		Integer weaponId = weaponType.getKey();
		WeaponDto weaponDto = new WeaponDto(weaponId, weaponType.getValue(), weaponStrengths.get(weaponId));
		weapons.put(weaponId, weaponDto);
	}

	private void validateWeaponsConfig(Map<Integer, String> weaponTypes, Map<Integer, Set<Integer>> weaponStrengths) {
		Set<Integer> weaponIds = weaponTypes.keySet();

		Assert.isTrue(weaponIds.equals(weaponStrengths.keySet()), "There must be one strength per weapon");
	}
}
