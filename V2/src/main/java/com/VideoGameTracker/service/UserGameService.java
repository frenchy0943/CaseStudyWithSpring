package com.VideoGameTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.VideoGameTracker.entities.Game;
import com.VideoGameTracker.entities.UserGame;
import com.VideoGameTracker.repo.UserGameRepository;

@Service
public class UserGameService {

	@Autowired
	UserGameRepository ugr;

	@Autowired
	GameService gs;

	@Autowired
	UserService us;

	public void deleteUserGame(UserGame ug) {
		ugr.delete(ug);
	}

	public void addUserGame(UserGame ug) {
		ugr.save(ug);
	}

	public UserGame getUserGame(String userName, String gameName) {
		return ugr.getUserGameByUserNameAndGameName(userName, gameName);
	}

	public void linkUserAndGame(String userName, String gameName, double hours, int timesCompleted, String list) {
		Game game = null;
		if (!ugr.existsByUserNameAndGameName(userName, gameName)) {
			if (!gs.exists(gameName)) {
				game = new Game(gameName);
				gs.addGame(game);
			} else {
				game = gs.getGame(gameName);
			}
			us.addToList(userName, game, list);
			UserGame ug = new UserGame(userName, gameName, hours, timesCompleted, list);
			ugr.save(ug);
		}
	}

	public void updateUserGame(String userName, String gameName, double hours, int timesCompleted, String list) {
		UserGame ug = getUserGame(userName, gameName);
		ug.setGameHours(hours);
		ug.setTimesCompleted(timesCompleted);
		us.removeFromList(userName, gs.getGame(gameName), ug.getCurrentList());
		us.addToList(userName, gs.getGame(gameName), list);
		ug.setCurrentList(list);
		ugr.save(ug);
	}

	@Transactional
	public void removeGame(String userName, String gameName) {
		UserGame ug = getUserGame(userName, gameName);
		ugr.deleteUserGameByUserNameAndGameName(userName, gameName);
		us.removeFromList(userName, gs.getGame(gameName), ug.getCurrentList());
	}

	public List<UserGame> getAllUserGamesById(String userName) {
		return ugr.findAllByUserName(userName);
	}

	public List<UserGame> getAllByGameNameSortByHours(String gameName) {
		return ugr.findAllByGameNameOrderByGameHoursDescTimesCompletedDesc(gameName);
	}
	
	public List<UserGame> getAllByGameNameSortByCompletions(String gameName){
		return ugr.findAllByGameNameOrderByTimesCompletedDescGameHoursDesc(gameName);
	}

	public void updateGameHours(String userName, String gameName, double hours) {
		UserGame ug = getUserGame(userName, gameName);
		ug.setGameHours(hours);
		ugr.save(ug);
	}
}
