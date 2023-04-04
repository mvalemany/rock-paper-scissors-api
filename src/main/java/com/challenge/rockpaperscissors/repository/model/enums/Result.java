package com.challenge.rockpaperscissors.repository.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Possible match outcomes
 *
 * @author mvalls
 */
public enum Result {

	TIE(0, getWinnerLiteral(null)),

	MACHINE_WINS(Player.MACHINE.getId(), getWinnerLiteral(Player.MACHINE)),

	HUMAN_WINS(Player.HUMAN.getId(), getWinnerLiteral(Player.HUMAN));

	private Integer value;
	private String literal;

	Result(int value, String literal) {
		this.value = value;
		this.literal = literal;
	}

	public Integer getValue() {
		return value;
	}

	@JsonValue
	public String getResult() {
		return literal;
	}

	private static String getWinnerLiteral(Player player) {
		String resultLiteral;

		if (player != null && player.getNickname() != null) {
			resultLiteral = player.getNickname().concat(" wins!");

		} else {
			resultLiteral = "It's a tie.";
		}

		return resultLiteral;
	}
}
