package com.VideoGameTracker.V2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.VideoGameTracker.entities.Game;
import com.VideoGameTracker.entities.User;
import com.VideoGameTracker.entities.UserGame;
import com.VideoGameTracker.repo.UserGameRepository;
import com.VideoGameTracker.service.GameService;
import com.VideoGameTracker.service.UserGameService;
import com.VideoGameTracker.service.UserService;

@SpringBootTest
class UserGameTest {

	@Autowired
	UserGameService ugs;
	
	@Autowired
	UserGameRepository ugr;
	
	@Autowired
	UserService us;
	
	@Autowired
	GameService gs;
	
	UserGame testUG;
	
	UserGame testUG2;
	
	UserGame testUG3;
	
	UserGame testUG4;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		us.addUser(new User("testUser", "Testpass", "Testpass"));
		us.addUser(new User("testUser2", "Testpass", "Testpass"));
		
		gs.addGame(new Game("testGame"));
		gs.addGame(new Game("testGame2"));
		gs.addGame(new Game("testGame3"));
		
		testUG = new UserGame("testUser", "testGame", 0.0, 0, "backlog");
		testUG2 = new UserGame("testUser", "testGame2", 0.0, 2, "current");
		testUG3 = new UserGame("testUser2", "testGame2", 15.0, 0, "current");
		testUG4 = new UserGame("testUser", "testGame3", 30.0, 1, "completed");
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	@Transactional
	void testLinkUserGame() {
		assertEquals(1, ugs.linkUserAndGame("testUser", "testGame", 0, 0, "backlog"));
	}
	
	@Test
	@Transactional
	void testGet() {
		ugr.save(testUG);
		assertEquals(testUG, ugs.getUserGame("testUser", "testGame"));
	}
	
	@Test
	@Transactional
	void testUpdate() {
		ugr.save(testUG);
		testUG.setCurrentList("completed");
		testUG.setGameHours(10.0);
		testUG.setTimesCompleted(1);
		assertEquals(testUG, ugs.updateUserGame("testUser", "testGame", 10.0, 1, "completed"));
	}
	
	@Test
	@Transactional
	void testDelete() {
		ugr.save(testUG);
		assertEquals(1, ugs.removeGame("testUser", "testGame"));
	}
	
	@Test
	@Transactional
	void testGetAllById() {
		ugr.save(testUG);
		ugr.save(testUG2);
		List<UserGame> expected = new ArrayList<>();
		expected.add(testUG);
		expected.add(testUG2);
		assertEquals(expected, ugs.getAllUserGamesById("testUser"));
	}
	
	@Test
	@Transactional
	void testSortByHours() {
		ugr.save(testUG2);
		ugr.save(testUG3);
		List<UserGame> expected = new ArrayList<>();
		expected.add(testUG3);
		expected.add(testUG2);
		assertEquals(expected, ugs.getAllByGameNameSortByHours("testGame2"));
	}
	
	@Test
	@Transactional
	void testSortByTimesCompleted() {
		ugr.save(testUG2);
		ugr.save(testUG3);
		List<UserGame> expected = new ArrayList<>();
		expected.add(testUG2);
		expected.add(testUG3);
		assertEquals(expected, ugs.getAllByGameNameSortByCompletions("testGame2"));
	}
	
	@Test
	@Transactional
	void testSortUserNameByHours() {
		ugr.save(testUG);
		ugr.save(testUG2);
		ugr.save(testUG4);
		List<UserGame> expected = new ArrayList<>();
		expected.add(testUG4);
		expected.add(testUG);
		expected.add(testUG2);
		assertEquals(expected, ugs.getAllByIdHoursSort("testUser"));
	}
	
	@Test
	@Transactional
	void testSortUserNameByCompletions() {
		ugr.save(testUG);
		ugr.save(testUG2);
		ugr.save(testUG4);
		List<UserGame> expected = new ArrayList<>();
		expected.add(testUG2);
		expected.add(testUG4);
		expected.add(testUG);
		assertEquals(expected, ugs.getAllByIdCompletionsSort("testUser"));
	}
	
	@Test
	@Transactional
	void testSortUserNameByList() {
		ugr.save(testUG);
		ugr.save(testUG2);
		ugr.save(testUG4);
		List<UserGame> expected = new ArrayList<>();
		expected.add(testUG);
		expected.add(testUG4);
		expected.add(testUG2);
		assertEquals(expected, ugs.getAllByIdListSort("testUser"));
	}

}
