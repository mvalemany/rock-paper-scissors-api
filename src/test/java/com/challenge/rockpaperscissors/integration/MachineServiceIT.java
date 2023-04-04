package com.challenge.rockpaperscissors.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.challenge.rockpaperscissors.dto.match.MoveDto;
import com.challenge.rockpaperscissors.service.MachineService;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MachineServiceIT {
	
	private static final Integer ROCK_ID = 1;
	private static final Integer PAPER_ID = 2;
	private static final Integer SCISSORS_ID = 3;
	
    private static final int NUM_ITERATIONS = 1000;
    private static final List<Integer> WEAPON_ID_LIST = Arrays.asList(ROCK_ID, PAPER_ID, SCISSORS_ID);
    
    @Autowired
    private MachineService machineService;

    @Test
    void shouldReturnAllWeaponTypesWhenGetMachineMove() {
    	Map<Integer, Boolean> isTypeReturnedMap = new HashMap<>();
    	isTypeReturnedMap.put(ROCK_ID, Boolean.FALSE);
    	isTypeReturnedMap.put(PAPER_ID, Boolean.FALSE);
    	isTypeReturnedMap.put(SCISSORS_ID, Boolean.FALSE);
    	
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            MoveDto moveDto = machineService.getMachineMove();
            assertNotNull(moveDto, "Move cannot be null");
            assertNotNull(moveDto.getWeaponId(), "Weapon id cannot be null");

            int weaponId = moveDto.getWeaponId();
            assertTrue(WEAPON_ID_LIST.contains(weaponId), "The machine must choose a valid weapon id");
            
            isTypeReturnedMap.put(weaponId, Boolean.TRUE);
        }
        
        boolean isAllTypesReturned = isTypeReturnedMap.values().stream().allMatch(value -> value == Boolean.TRUE);
        assertTrue(isAllTypesReturned, "All weapon types must be chosen after 1000 iterations");
    }

}
