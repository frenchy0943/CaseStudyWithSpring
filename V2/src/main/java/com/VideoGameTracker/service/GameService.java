package com.VideoGameTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VideoGameTracker.entities.Game;
import com.VideoGameTracker.repo.GameRepository;

@Service
public class GameService {

	@Autowired
	GameRepository gr;

	public void addGame(Game game) {
		gr.save(game);
	}
	
	public Game getGame(String name) {
		return gr.getGameByName(name);
	}
	
	public boolean exists(String gameName) {
		return gr.existsById(gameName);
	}
	
	public List<Game> getAllGames(){
		return gr.findAll();
	}
	
}

