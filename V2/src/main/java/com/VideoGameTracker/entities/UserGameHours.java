package com.VideoGameTracker.entities;

public class UserGameHours {
	
	private String userName;
	
	private String gameName;
	
	private double gameHours;
	
	public UserGameHours() {
		
	}
	
	public UserGameHours(String userName, String gameName, double gameHours) {
		this.userName = userName;
		this.gameName = gameName;
		this.gameHours = gameHours;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public double getGameHours() {
		return gameHours;
	}

	public void setGameHours(double gameHours) {
		this.gameHours = gameHours;
	}
	
	

}
