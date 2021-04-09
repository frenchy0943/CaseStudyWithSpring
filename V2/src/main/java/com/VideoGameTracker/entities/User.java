package com.VideoGameTracker.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class User {

	@Id
	private String userName;
	
	private String password;
	
	@Transient
	private String passwordVerification;
	
	@ManyToMany(
			targetEntity = Game.class,
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
			)
	@JoinTable(name = "user_backlog")
	private List<Game> backLogGames = new ArrayList<Game>();
	@ManyToMany(
			targetEntity = Game.class,
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
			)
	@JoinTable(name = "user_current")
	private List<Game> currentGames = new ArrayList<Game>();
	@ManyToMany(
			targetEntity = Game.class,
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
			)
	@JoinTable(name="user_completed")
	private List<Game> completedGames = new ArrayList<Game>();
	
	public User() {
	
	}
	
	public User(String userName, String password, String passwordVerification) {
		this.userName = userName;
		this.password = password;
		this.passwordVerification = passwordVerification;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Game> getBackLogGames() {
		return backLogGames;
	}

	public void setBackLogGames(List<Game> backLogGames) {
		this.backLogGames = backLogGames;
	}

	public List<Game> getCurrentGames() {
		return currentGames;
	}

	public void setCurrentGames(List<Game> currentGames) {
		this.currentGames = currentGames;
	}

	public List<Game> getCompletedGames() {
		return completedGames;
	}

	public void setCompletedGames(List<Game> completedGames) {
		this.completedGames = completedGames;
	}

	public String getPasswordVerification() {
		return passwordVerification;
	}

	public void setPasswordVerification(String passwordVerification) {
		this.passwordVerification = passwordVerification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backLogGames == null) ? 0 : backLogGames.hashCode());
		result = prime * result + ((completedGames == null) ? 0 : completedGames.hashCode());
		result = prime * result + ((currentGames == null) ? 0 : currentGames.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (backLogGames == null) {
			if (other.backLogGames != null)
				return false;
		} else if (!backLogGames.equals(other.backLogGames))
			return false;
		if (completedGames == null) {
			if (other.completedGames != null)
				return false;
		} else if (!completedGames.equals(other.completedGames))
			return false;
		if (currentGames == null) {
			if (other.currentGames != null)
				return false;
		} else if (!currentGames.equals(other.currentGames))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		return "User [userName=" + userName + ", password=" + password + ", backLogGames=" + backLogGames
				+ ", currentGames=" + currentGames + ", completedGames=" + completedGames + "]";
	}

	
	
}
