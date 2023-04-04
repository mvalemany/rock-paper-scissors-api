package com.challenge.rockpaperscissors.service.impl;

import java.util.List;
import java.util.Random;

import com.challenge.rockpaperscissors.dto.match.MoveDto;
import com.challenge.rockpaperscissors.service.MachineService;

/**
 * Implements {@link MachineService}
 *
 * @author mvalls
 */
public class MachineServiceImpl implements MachineService {

	private static final int FIRST_INDEX = 0;

	private List<Integer> weaponIdList;

	/**
	 * Included in the random index chosen
	 */
	private int origin;

	/**
	 * Excluded from the random index chosen
	 */
	private int bound;

	/**
	 * Random generator
	 */
	private Random random;

	/**
	 * Constructor
	 *
	 * @param weaponIdList
	 * @param random
	 */
	public MachineServiceImpl(List<Integer> weaponIdList, Random random) {
		this.origin = FIRST_INDEX;
		this.bound = weaponIdList.size();
		this.weaponIdList = weaponIdList;
		this.random = random;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MoveDto getMachineMove() {
		return new MoveDto(chooseWeaponId());
	}

	private int chooseWeaponId() {
		return weaponIdList.get(this.random.nextInt(origin, bound));
	}

}
