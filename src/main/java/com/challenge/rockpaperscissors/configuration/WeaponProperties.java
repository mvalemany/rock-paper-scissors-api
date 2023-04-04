package com.challenge.rockpaperscissors.configuration;

import java.util.Map;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Weapons data
 * 
 * @author mvalls
 */
@Data
@Component
@ConfigurationProperties(prefix = "weapons")
public class WeaponProperties {
	
	private Map<Integer, String> types; 

	private Map<Integer, Set<Integer>> strengths; 
	
}
