package com.VideoGameTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VideoGameTracker.entities.Game;
import com.VideoGameTracker.repo.GameRepository;

/**
 * Services for the game entity
 * @author Brandon French
 *
 */
@Service
public class GameService {

	@Autowired
	GameRepository gr;
	/**
	 * Save a game to the database
	 * @param game Game to be added to the database
	 */
	public void addGame(Game game) {
		gr.save(game);
	}
	/**
	 * Find a game in the database
	 * @param name the name of the game to be found
	 * @return The game found in the database
	 */
	public Game getGame(String name) {
		return gr.getGameByName(name);
	}
	/**
	 * Check if game exists in database already
	 * @param gameName the name of the game to check
	 * @return true if exists false otherwise
	 */
	public boolean exists(String gameName) {
		return gr.existsById(gameName);
	}
	/**
	 * Find all the games in the database
	 * @return a List of all games currently in the database
	 */
	public List<Game> getAllGames(){
		return gr.findAll();
	}
	
}

