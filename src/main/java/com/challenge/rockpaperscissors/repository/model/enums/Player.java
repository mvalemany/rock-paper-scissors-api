package com.challenge.rockpaperscissors.repository.model.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Players involved in the match
 *
 * @author mvalls
 */
public enum Player {

	MACHINE(1, "The machine"),

	HUMAN(2, "The human");

	private Integer id;
	private String nickname;

	private static final Map<Integer, Player> lookup = new HashMap<>();

	static {
		for (Player player : Player.values()) {
			lookup.put(player.getId(), player);
		}
	}

	Player(int id, String nickname) {
		this.id = id;
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	@JsonValue
	public String getNickname() {
		return nickname;
	}

	public static Player getById(Integer id) {
		return lookup.get(id);
	}
}
