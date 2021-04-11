package com.VideoGameTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VideoGameTracker.entities.Game;
import com.VideoGameTracker.entities.User;
import com.VideoGameTracker.repo.UserRepository;

/**
 * Service class for the User entity
 * @author Brandon French
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	@Autowired
	UserGameService ugs;
	
	/**
	 * Save a user to the database
	 * @param user the user to be added to the database
	 */
	public void addUser(User user) {
		ur.save(user);
	}
	
	/**
	 * Get a user out of the database by their userName
	 * @param userName user name of the user we are trying to find
	 * @return the user found in the database
	 */
	public User getById(String userName) {
		return ur.getUserByUserName(userName);
	}
	
	/**
	 * Get All the users currently in the database
	 * @return a list of all Users in the database
	 */
	public List<User> getAllUsers(){
		return ur.findAll();
	}
	
	/**
	 * Check if user exists, and if the password passed in matches what's in the databse
	 * @param userName userName of user we are trying to find
	 * @param password password passed in from front end
	 * @return true if user is valid, false otherwise
	 */
	public boolean validateUser(String userName, String password) {
		boolean flag = false;
		User user = ur.getUserByUserName(userName);
		if(user != null && user.getPassword().equals(password)) {
			flag = true;
		}
		return flag;
	}
	/**
	 * Register a new user to the database
	 * @param userName userName the user is attempting to use
	 * @param password password they are attempting to use
	 * @param passwordVerification check to see if password was typed succesfully two times
	 * @return true of user was registered, false otherwise
	 */
	public boolean registerUser(String userName, String password, String passwordVerification) {
		if(!ur.existsById(userName) && password.equals(passwordVerification)) {
			ur.save(new User(userName, password, passwordVerification));
			return true;
		}
		
		return false;
	}
	/**
	 * Add a game to a users list
	 * @param userName user name of the user we are trying to modify
	 * @param game game to be added to a list
	 * @param listToAdd list we are going to add to
	 * @return 1 if game added successfully, 0 otherwise
	 */
	public int addToList(String userName, Game game, String listToAdd) {
		User user = getById(userName);
		int added = 0;
		if (listToAdd.equals("current")) {
			user.getCurrentGames().add(game);
			added = 1;
		} else if (listToAdd.equals("backlog")) {
			user.getBackLogGames().add(game);
			added = 1;
		} else if (listToAdd.equals("completed")) {
			user.getCompletedGames().add(game);
			added = 1;
		}
		ur.save(user);
		return added;
	}
	
	/**
	 * Remove a game from a user's list
	 * @param userName user name of the user we are trying to modify
	 * @param game game to be removed from the list
	 * @param listToRemove list we are going to remove from
	 * @return 1 if game removed successfully, 0 otherwise
	 */
	public int removeFromList(String userName, Game game, String listToRemove) {
		User user = getById(userName);
		int removed = 0;
		if (listToRemove.equals("current")) {
			System.out.println("Current List Before: " + user.getCurrentGames());
			user.getCurrentGames().remove(game);
			removed = 1;
		} else if (listToRemove.equals("backlog")) {
			System.out.println("Backlog List Before: " + user.getBackLogGames());
			user.getBackLogGames().remove(game);
			removed = 1;
		} else if (listToRemove.equals("completed")) {
			System.out.println("Completed List Before: " + user.getCompletedGames());
			user.getCompletedGames().remove(game);
			removed = 1;
		}
		ur.save(user);
		return removed;
	}
	/**
	 * Change the list of a game for a certain user
	 * @param userName user to change lists for
	 * @param toRemove list to remove the game from
	 * @param toAdd list to add the game to
	 * @param game game being moved
	 */
	public void changeList(String userName, String toRemove, String toAdd, Game game) {
		addToList(userName, game, toAdd);
		removeFromList(userName, game, toRemove);
	}
	
}
