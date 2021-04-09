package com.VideoGameTracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VideoGameTracker.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, String>{
	
	Game getGameByName(String name);

}
