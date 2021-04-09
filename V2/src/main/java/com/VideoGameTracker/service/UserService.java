package com.VideoGameTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VideoGameTracker.entities.Game;
import com.VideoGameTracker.entities.User;
import com.VideoGameTracker.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	@Autowired
	UserGameService ugs;
	
	//Create
	public void addUser(User user) {
		ur.save(user);
	}
	
	public User getById(String userName) {
		return ur.getUserByUserName(userName);
	}
	
	public List<User> getAllUsers(){
		return ur.findAll();
	}
	
	public boolean validateUser(String userName, String password) {
		boolean flag = false;
		User user = ur.getUserByUserName(userName);
		if(user != null && user.getPassword().equals(password)) {
			flag = true;
		}
		return flag;
	}
	
	public boolean registerUser(String userName, String password, String passwordVerification) {
		if(!ur.existsById(userName) && password.equals(passwordVerification)) {
			ur.save(new User(userName, password, passwordVerification));
			return true;
		}
		
		return false;
	}
	
	public void addToList(String userName, Game game, String listToAdd) {
		User user = getById(userName);
		if (listToAdd.equals("current")) {
			user.getCurrentGames().add(game);
		} else if (listToAdd.equals("backlog")) {
			user.getBackLogGames().add(game);
		} else if (listToAdd.equals("completed")) {
			user.getCompletedGames().add(game);
		}
		ur.save(user);
	}
	
	public void removeFromList(String userName, Game game, String listToRemove) {
		User user = getById(userName);
		if (listToRemove.equals("current")) {
			System.out.println("Current List Before: " + user.getCurrentGames());
			user.getCurrentGames().remove(game);
		} else if (listToRemove.equals("backlog")) {
			System.out.println("Backlog List Before: " + user.getBackLogGames());
			user.getBackLogGames().remove(game);
		} else if (listToRemove.equals("completed")) {
			System.out.println("Completed List Before: " + user.getCompletedGames());
			user.getCompletedGames().remove(game);
		}
		System.out.println("Current List After: " + user.getCurrentGames());
		System.out.println("Backlog List After: " + user.getBackLogGames());
		System.out.println("Completed List After: " + user.getCompletedGames());
		ur.save(user);
	}
	
	public void changeList(String userName, String toRemove, String toAdd, Game game) {
		addToList(userName, game, toAdd);
		removeFromList(userName, game, toRemove);
	}
	
}
