package com.VideoGameTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.VideoGameTracker.entities.Game;
import com.VideoGameTracker.entities.UserGame;
import com.VideoGameTracker.repo.UserGameRepository;

/**
 * Services class for the UserGame entity
 * @author Brandon French
 *
 */
@Service
public class UserGameService {

	@Autowired
	UserGameRepository ugr;

	@Autowired
	GameService gs;

	@Autowired
	UserService us;

	/**
	 * Get a UserGame out of the database
	 * @param userName the user name of the current user
	 * @param gameName the name of the game linked to the current user
	 * @return the UserGame linked to the current user and game
	 */
	public UserGame getUserGame(String userName, String gameName) {
		return ugr.getUserGameByUserNameAndGameName(userName, gameName);
	}

	/**
	 * Create a userGame object and add it to the database
	 * @param userName the userName of the current user
	 * @param gameName the name of the game to be linked to the user
	 * @param hours how many hours the user has played this game
	 * @param timesCompleted how many times the user has completed this game
	 * @param list the name of the list the game will be added to
	 * @return 0 if the userGame has already been added to the list, 1 if it is added now
	 */
	public int linkUserAndGame(String userName, String gameName, double hours, int timesCompleted, String list) {
		Game game = null;
		int result = 0;
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
			result = 1;;
		}
		return result;
	}

	/**
	 * Update the details of a UserGame
	 * @param userName user name of the current user
	 * @param gameName name of the game whose details are to be updated
	 * @param hours new total hours played
	 * @param timesCompleted new total times completed
	 * @param list new list to be added to
	 * @return the updated UserGame
	 */
	public UserGame updateUserGame(String userName, String gameName, double hours, int timesCompleted, String list) {
		UserGame ug = getUserGame(userName, gameName);
		ug.setGameHours(hours);
		ug.setTimesCompleted(timesCompleted);
		us.removeFromList(userName, gs.getGame(gameName), ug.getCurrentList());
		us.addToList(userName, gs.getGame(gameName), list);
		ug.setCurrentList(list);
		ugr.save(ug);
		return ug;
	}

	/**
	 * Remove a userGame from the database
	 * @param userName user name of the current user
	 * @param gameName name of the game trying to be removed
	 * @return the number of rows affected in the database
	 */
	@Transactional
	public int removeGame(String userName, String gameName) {
		UserGame ug = getUserGame(userName, gameName);
		int result = ugr.deleteUserGameByUserNameAndGameName(userName, gameName);
		us.removeFromList(userName, gs.getGame(gameName), ug.getCurrentList());
		return result;
	}

	/**
	 * Get all UserGames for a particular user
	 * @param userName the userName (id) for whom the UserGames belong to
	 * @return a list of all UserGames for this user
	 */
	public List<UserGame> getAllUserGamesById(String userName) {
		return ugr.findAllByUserName(userName);
	}

	/**
	 * Get all UserGame objects for a particular game sorted by hours
	 * @param gameName the name of the game linked to the UserGames
	 * @return a list of all UserGames sorted by hours played
	 */
	public List<UserGame> getAllByGameNameSortByHours(String gameName) {
		return ugr.findAllByGameNameOrderByGameHoursDescTimesCompletedDesc(gameName);
	}
	
	/**
	 * Get all UserGame objects for a particular game sorted by completions
	 * @param gameName the name of the game linked to the UserGames
	 * @return a list of all UserGames sorted by completions
	 */
	public List<UserGame> getAllByGameNameSortByCompletions(String gameName){
		return ugr.findAllByGameNameOrderByTimesCompletedDescGameHoursDesc(gameName);
	}
	
	/**
	 * Get all UserGame objects for a particular user sorted by hours
	 * @param userName the user name linked to all the UserGames
	 * @return a list of all userGames sorted by hours
	 */
	public List<UserGame> getAllByIdHoursSort(String userName){
		return ugr.findAllByUserNameOrderByGameHoursDesc(userName);
	}
	
	/**
	 * Get all UserGame objects for a particular user sorted by completions
	 * @param userName the user name linked to all the UserGames
	 * @return a list of all userGames sorted by completions
	 */
	public List<UserGame> getAllByIdCompletionsSort(String userName){
		return ugr.findAllByUserNameOrderByTimesCompletedDesc(userName);
	}
	/**
	 * Get all UserGame objects for a particular user sorted by list
	 * @param userName the user name linked to all the UserGames
	 * @return a list of all userGames sorted by list
	 */
	public List<UserGame> getAllByIdListSort(String userName){
		return ugr.findAllByUserNameOrderByCurrentList(userName);
	}

	/**
	 * Update the number of hours played on a particular game, by a particular user
	 * @param userName the user name of the user
	 * @param gameName the name of the game
	 * @param hours how many hours to add to the total hours played for a user
	 */
	public void updateGameHours(String userName, String gameName, double hours) {
		UserGame ug = getUserGame(userName, gameName);
		ug.setGameHours(hours);
		ugr.save(ug);
	}
}
