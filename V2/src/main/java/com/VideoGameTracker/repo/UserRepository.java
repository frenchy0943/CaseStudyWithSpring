package com.VideoGameTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VideoGameTracker.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	User getUserByUserName(String userName);
	
}