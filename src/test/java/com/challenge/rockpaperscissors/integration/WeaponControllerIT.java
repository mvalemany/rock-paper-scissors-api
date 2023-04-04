package com.challenge.rockpaperscissors.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.challenge.rockpaperscissors.dto.weapon.WeaponDto;
import com.challenge.rockpaperscissors.dto.weapon.WeaponListDto;
import com.challenge.rockpaperscissors.integration.config.BaseIT;

public class WeaponControllerIT extends BaseIT {
	
	private static final int ONE_ELEMENT = 1;
	private static final int FIRST_ELEMENT = 0;
	
	private static final Integer ROCK_ID = 1;
	private static final Integer PAPER_ID = 2;
	private static final Integer SCISSORS_ID = 3;
	private static final String ROCK_NAME = "rockTest";
	private static final String PAPER_NAME = "paperTest";
	private static final String SCISSORS_NAME = "scissorsTest";
	private static final Set<Integer> ROCK_STRENGTHS = Collections.singleton(SCISSORS_ID);
	private static final Set<Integer> PAPER_STRENGTHS = Collections.singleton(ROCK_ID);
	private static final Set<Integer> SCISSORS_STRENGTHS = Collections.singleton(PAPER_ID);

	private static Map<Integer, String> types; 
	private static Map<Integer, Set<Integer>> strengths; 
	
    private String weaponGetListUri = "/weapon/getList";
    
    @BeforeAll
    public static void init() {
    	types = new HashMap<>();
    	types.put(ROCK_ID, ROCK_NAME);
    	types.put(PAPER_ID, PAPER_NAME);
    	types.put(SCISSORS_ID, SCISSORS_NAME);

    	strengths = new HashMap<>();
    	strengths.put(ROCK_ID, ROCK_STRENGTHS);
    	strengths.put(PAPER_ID, PAPER_STRENGTHS);
    	strengths.put(SCISSORS_ID, SCISSORS_STRENGTHS);
    }
    
    @Test
    void shouldGetWeapons() {

        String url = getUrl(weaponGetListUri);
		ResponseEntity<WeaponListDto> response = restTemplate.getForEntity(
        		url,
                WeaponListDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Code status must be 200");

        WeaponListDto body = response.getBody();
		assertEquals(WeaponListDto.class, body.getClass(), "Body object must be a WeaponListDto");

		int totalRetrievedWeapons = body.getWeapons().size();
		assertEquals(types.size(), totalRetrievedWeapons, "There must be equal number of weapons and types");
		assertEquals(strengths.size(), totalRetrievedWeapons, "There must be equal number of weapons and strengths");

        for (Entry<Integer, String> type : types.entrySet()) {
            List<WeaponDto> list = body.getWeapons()
            		.stream().filter(
            				w -> w.getId() == type.getKey())
            		.toList();

            assertEquals(ONE_ELEMENT, list.size(), "Cannot have more than one weapon of the same type");

            WeaponDto retrievedWeapon = list.get(FIRST_ELEMENT);
            assertEquals(type.getValue(), retrievedWeapon.getName(), "Weapon type doesn't match");
            assertEquals(strengths.get(type.getKey()), retrievedWeapon.getStrengths(), "Weapon strength doesn't match");
        }
    }

}
