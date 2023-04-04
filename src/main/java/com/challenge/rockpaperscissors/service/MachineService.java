package com.challenge.rockpaperscissors.service;

import com.challenge.rockpaperscissors.dto.match.MoveDto;

/**
 * Machine's abstraction
 *
 * @author mvalls
 */
public interface MachineService {

	/**
	 * Generates the machine's move
	 *
	 * @return {@link MoveDto}
	 */
	public MoveDto getMachineMove();

}
