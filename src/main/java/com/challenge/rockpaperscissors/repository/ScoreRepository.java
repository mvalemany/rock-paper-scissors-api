package com.challenge.rockpaperscissors.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.rockpaperscissors.repository.model.Score;

/**
 * Repository for {@link Score}
 *
 * @author mvalls
 */
public interface ScoreRepository extends JpaRepository<Score, Integer>{

	public List<Score> findByPlayerIdIn(List<Integer> playerIdList);

	public List<Score> findByOrderByVictoriesDesc();

}
