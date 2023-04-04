package com.challenge.rockpaperscissors.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.rockpaperscissors.dto.match.MatchDto;
import com.challenge.rockpaperscissors.dto.match.MoveDto;
import com.challenge.rockpaperscissors.dto.match.MoveResponseDto;
import com.challenge.rockpaperscissors.dto.weapon.WeaponDto;
import com.challenge.rockpaperscissors.repository.MatchRepository;
import com.challenge.rockpaperscissors.repository.model.Match;
import com.challenge.rockpaperscissors.repository.model.enums.Player;
import com.challenge.rockpaperscissors.repository.model.enums.Result;
import com.challenge.rockpaperscissors.service.MachineService;
import com.challenge.rockpaperscissors.service.MatchService;
import com.challenge.rockpaperscissors.service.ScoreService;

import lombok.extern.slf4j.Slf4j;

/**
 * Implements {@link MatchService}
 *
 * @author mvalls
 */
@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	MachineService machineService;

	@Autowired
	ScoreService scoreService;

	@Autowired
	Map<Integer, WeaponDto> weapons;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MatchDto playMatch(MoveDto humanMove) {

		MoveResponseDto playerOneMove = getMoveResponseDto(machineService.getMachineMove(), Player.MACHINE);
		log.info("Machine weapon: " + playerOneMove.getWeapon().getName());

		MoveResponseDto playeTwoMove = getMoveResponseDto(humanMove, Player.HUMAN);
		log.info("Human weapon: " + playeTwoMove.getWeapon().getName());

		Result result = getResult(playerOneMove, playeTwoMove);
		log.info(result.getResult());

		saveMatch(playerOneMove, playeTwoMove, result);
		MatchDto matchDto = new MatchDto(playerOneMove, playeTwoMove, result);
		scoreService.saveScores(matchDto);

		return matchDto;
	}

	private MoveResponseDto getMoveResponseDto(MoveDto playerMove, Player player) {
		return new MoveResponseDto(
				player,
				getWeapon(playerMove.getWeaponId()));
	}

	private WeaponDto getWeapon(Integer weaponId) {
		return weapons.get(weaponId);
	}

	private Result getResult(MoveResponseDto machineMove, MoveResponseDto humanMove) {
		Result result = Result.TIE;

		if (firstWeaponWins(machineMove.getWeapon(), humanMove.getWeapon())) {
			result = Result.MACHINE_WINS;

		} else if (firstWeaponWins(humanMove.getWeapon(), machineMove.getWeapon())){
			result = Result.HUMAN_WINS;
		}

		return result;
	}

	private boolean firstWeaponWins(WeaponDto firstWeapon, WeaponDto secondWeapon) {
		return firstWeapon.getStrengths().contains(secondWeapon.getId());
	}

	private void saveMatch(MoveResponseDto playerOneMove, MoveResponseDto playeTwoMove, Result result) {
		int playerOneId = playerOneMove.getPlayer().getId();
		int playerTwoId = playeTwoMove.getPlayer().getId();

		Match match = new Match();
		match.setPlayerOne(playerOneId);
		match.setPlayerTwo(playerTwoId);
		match.setWeaponOne(playerOneMove.getWeapon().getId());
		match.setWeaponTwo(playeTwoMove.getWeapon().getId());
		match.setResult(result.getValue());
		matchRepository.save(match);
	}

}
