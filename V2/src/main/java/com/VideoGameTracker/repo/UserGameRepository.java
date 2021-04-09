package com.VideoGameTracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VideoGameTracker.entities.UserGame;

public interface UserGameRepository extends JpaRepository<UserGame, String>{

	UserGame getUserGameByUserNameAndGameName(String userName, String gameName);
	
	void deleteUserGameByUserNameAndGameName(String userName, String gameName);
	
	List<UserGame> findAllByUserName(String userName);
	
	List<UserGame> findAllByGameNameOrderByGameHoursDescTimesCompletedDesc(String gameName);
	
	List<UserGame> findAllByGameNameOrderByTimesCompletedDescGameHoursDesc(String gameName);
	
	boolean existsByUserNameAndGameName(String userName, String gameName);
}
