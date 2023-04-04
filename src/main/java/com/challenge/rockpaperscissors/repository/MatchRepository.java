package com.challenge.rockpaperscissors.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.rockpaperscissors.repository.model.Match;

/**
 * Repository for {@link Match}
 *
 * @author mvalls
 */
public interface MatchRepository extends JpaRepository<Match, Integer> {

}
