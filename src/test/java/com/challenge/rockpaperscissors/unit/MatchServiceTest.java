package com.challenge.rockpaperscissors.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.rockpaperscissors.dto.match.MatchDto;
import com.challenge.rockpaperscissors.dto.match.MoveDto;
import com.challenge.rockpaperscissors.dto.weapon.WeaponDto;
import com.challenge.rockpaperscissors.repository.MatchRepository;
import com.challenge.rockpaperscissors.repository.model.enums.Result;
import com.challenge.rockpaperscissors.service.MachineService;
import com.challenge.rockpaperscissors.service.ScoreService;
import com.challenge.rockpaperscissors.service.impl.MatchServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MatchServiceTest {
	
	private static final int ROCK_ID = 1;
	private static final int PAPER_ID = 2;
	private static final int SCISSORS_ID = 3;
	
	private static WeaponDto rockWeapon;
	private static WeaponDto paperWeapon;
	private static WeaponDto scissorsWeapon;

	private static MoveDto rockMove = new MoveDto(ROCK_ID);
	private static MoveDto paperMove = new MoveDto(PAPER_ID);
	private static MoveDto scissorsMove = new MoveDto(SCISSORS_ID);
	
	@InjectMocks
	MatchServiceImpl matchService;
	
	@Mock
	MatchRepository matchRepositoryMock;
	
	@Mock
	MachineService machineMoveServiceMock;

	@Mock
	ScoreService scoreServiceMock;

	@Mock
	Map<Integer, WeaponDto> weaponsListMock;

	@BeforeAll
	public static void init() {
		HashSet<Integer> rockDefeatableWeapons = new HashSet<>();
		rockDefeatableWeapons.add(SCISSORS_ID);
		rockWeapon = new WeaponDto(ROCK_ID, "rock", rockDefeatableWeapons);
		
		HashSet<Integer> paperDefeatableWeapons = new HashSet<>();
		paperDefeatableWeapons.add(ROCK_ID);
		paperWeapon = new WeaponDto(PAPER_ID, "paper", paperDefeatableWeapons);
		
		HashSet<Integer> scissorsDefeatableWeapons = new HashSet<>();
		scissorsDefeatableWeapons.add(PAPER_ID);
		scissorsWeapon = new WeaponDto(SCISSORS_ID, "scissors", scissorsDefeatableWeapons);
		
	}
	
	@Test
	void shouldTieWhenRockVsRock() {		
		when(weaponsListMock.get(ROCK_ID)).thenReturn(rockWeapon);
		when(machineMoveServiceMock.getMachineMove()).thenReturn(rockMove);
		
		MatchDto matchDto = matchService.playMatch(rockMove);
		
		assertEquals(ROCK_ID, matchDto.getPlayerOneMove().getWeapon().getId());
		assertEquals(Result.TIE, matchDto.getResult());
	}
	
	@Test
	void shouldWinMachineWhenPlayerUsesRockAndMachineUsesPaper() {		
		when(weaponsListMock.get(ROCK_ID)).thenReturn(rockWeapon);
		when(weaponsListMock.get(PAPER_ID)).thenReturn(paperWeapon);
		when(machineMoveServiceMock.getMachineMove()).thenReturn(paperMove);
		
		MatchDto matchDto = matchService.playMatch(rockMove);
		
		assertEquals(PAPER_ID, matchDto.getPlayerOneMove().getWeapon().getId());
		assertEquals(Result.MACHINE_WINS, matchDto.getResult());
	}
	
	@Test
	void shouldWinHumanWhenHumanUsesRockAndMachineUsesScissors() {		
		when(weaponsListMock.get(ROCK_ID)).thenReturn(rockWeapon);
		when(weaponsListMock.get(SCISSORS_ID)).thenReturn(scissorsWeapon);
		when(machineMoveServiceMock.getMachineMove()).thenReturn(scissorsMove);
		
		MatchDto matchDto = matchService.playMatch(rockMove);
		
		assertEquals(SCISSORS_ID, matchDto.getPlayerOneMove().getWeapon().getId());
		assertEquals(Result.HUMAN_WINS, matchDto.getResult());
	}
	
	@Test
	void shouldWinHumanWhenHumanUsesPaperAndMachineUsesRock() {		
		when(weaponsListMock.get(PAPER_ID)).thenReturn(paperWeapon);
		when(weaponsListMock.get(ROCK_ID)).thenReturn(rockWeapon);
		when(machineMoveServiceMock.getMachineMove()).thenReturn(rockMove);

		MatchDto matchDto = matchService.playMatch(paperMove);
		
		assertEquals(ROCK_ID, matchDto.getPlayerOneMove().getWeapon().getId());
		assertEquals(Result.HUMAN_WINS, matchDto.getResult());
	}

	@Test
	void shouldTieWhenPaperVsPaper() {		
		when(weaponsListMock.get(PAPER_ID)).thenReturn(paperWeapon);
		when(machineMoveServiceMock.getMachineMove()).thenReturn(paperMove);
		
		MatchDto matchDto = matchService.playMatch(paperMove);
		
		assertEquals(PAPER_ID, matchDto.getPlayerOneMove().getWeapon().getId());
		assertEquals(Result.TIE, matchDto.getResult());
	}
	
	@Test
	void shouldWinMachineWhenHumanUsesPaperAndMachineUsesScissors() {		
		when(weaponsListMock.get(PAPER_ID)).thenReturn(paperWeapon);
		when(weaponsListMock.get(SCISSORS_ID)).thenReturn(scissorsWeapon);
		when(machineMoveServiceMock.getMachineMove()).thenReturn(scissorsMove);

		MatchDto matchDto = matchService.playMatch(paperMove);
		
		assertEquals(SCISSORS_ID, matchDto.getPlayerOneMove().getWeapon().getId());
		assertEquals(Result.MACHINE_WINS, matchDto.getResult());
	}
	
	@Test
	void shouldWinMachineWhenHumanUsesScissorsAndMachineUsesRock() {		
		when(weaponsListMock.get(SCISSORS_ID)).thenReturn(scissorsWeapon);
		when(weaponsListMock.get(ROCK_ID)).thenReturn(rockWeapon);
		when(machineMoveServiceMock.getMachineMove()).thenReturn(rockMove);
		
		MatchDto matchDto = matchService.playMatch(scissorsMove);
		
		assertEquals(ROCK_ID, matchDto.getPlayerOneMove().getWeapon().getId());
		assertEquals(Result.MACHINE_WINS, matchDto.getResult());
	}
	
	@Test
	void shouldWinMachineWhenHumanUsesScissorsAndMachineUsesPaper() {		
		when(weaponsListMock.get(SCISSORS_ID)).thenReturn(scissorsWeapon);
		when(weaponsListMock.get(PAPER_ID)).thenReturn(paperWeapon);
		when(machineMoveServiceMock.getMachineMove()).thenReturn(paperMove);

		MatchDto matchDto = matchService.playMatch(scissorsMove);
	
		assertEquals(PAPER_ID, matchDto.getPlayerOneMove().getWeapon().getId());
		assertEquals(Result.HUMAN_WINS, matchDto.getResult());
	}

	@Test
	void shouldTieWhenScissorsVsScissors() {		
		when(weaponsListMock.get(SCISSORS_ID)).thenReturn(scissorsWeapon);
		when(machineMoveServiceMock.getMachineMove()).thenReturn(scissorsMove);
		
		MatchDto matchDto = matchService.playMatch(scissorsMove);
		
		assertEquals(SCISSORS_ID, matchDto.getPlayerOneMove().getWeapon().getId());
		assertEquals(Result.TIE, matchDto.getResult());
	}

}
