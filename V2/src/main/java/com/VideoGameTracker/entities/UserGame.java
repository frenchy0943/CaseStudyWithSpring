package com.VideoGameTracker.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(value=UserGameId.class)
@Table(name = "linkedGames")
public class UserGame {
	
	@Id
	private String userName;
	@Id
	private String gameName;
	private Double gameHours;
	private Integer timesCompleted;
	private String currentList;
	
	public UserGame() {
		userName = "";
		gameName = "";
		gameHours = 0.0;
		timesCompleted = 0;
		currentList = "";
	}

	public UserGame(String userName, String gameName, Double gameHours, Integer timesCompleted, String currentList) {
		super();
		this.userName = userName;
		this.gameName = gameName;
		this.gameHours = gameHours;
		this.timesCompleted = timesCompleted;
		this.currentList = currentList;
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

	public Double getGameHours() {
		return gameHours;
	}

	public void setGameHours(Double gameHours) {
		this.gameHours = gameHours;
	}

	public Integer getTimesCompleted() {
		return timesCompleted;
	}

	public void setTimesCompleted(Integer timesCompleted) {
		this.timesCompleted = timesCompleted;
	}

	public String getCurrentList() {
		return currentList;
	}

	public void setCurrentList(String currentList) {
		this.currentList = currentList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentList == null) ? 0 : currentList.hashCode());
		result = prime * result + ((gameHours == null) ? 0 : gameHours.hashCode());
		result = prime * result + ((gameName == null) ? 0 : gameName.hashCode());
		result = prime * result + ((timesCompleted == null) ? 0 : timesCompleted.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserGame other = (UserGame) obj;
		if (currentList == null) {
			if (other.currentList != null)
				return false;
		} else if (!currentList.equals(other.currentList))
			return false;
		if (gameHours == null) {
			if (other.gameHours != null)
				return false;
		} else if (!gameHours.equals(other.gameHours))
			return false;
		if (gameName == null) {
			if (other.gameName != null)
				return false;
		} else if (!gameName.equals(other.gameName))
			return false;
		if (timesCompleted == null) {
			if (other.timesCompleted != null)
				return false;
		} else if (!timesCompleted.equals(other.timesCompleted))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserGame [userName=" + userName + ", gameName=" + gameName + ", gameHours=" + gameHours
				+ ", timesCompleted=" + timesCompleted + ", currentList=" + currentList + "]";
	}
	
	

}

class UserGameId implements Serializable{
	private static final long serialVersionUid = -1L;
	@Id
	private String userName;
	@Id
	private String gameName;
	



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


}


