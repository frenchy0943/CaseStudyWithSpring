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
import com.VideoGameTracker.repo.GameRepository;
import com.VideoGameTracker.service.GameService;

@SpringBootTest
class GameTest {
	
	@Autowired
	GameService gs;
	
	@Autowired
	GameRepository gr;
	
	Game testGame;
	
	Game testGame2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testGame = new Game("testGame");
		testGame2 = new Game("testGame2");
		gr.save(testGame);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Transactional
	void testAdd() {
		gs.addGame(testGame);
		assertTrue(gr.existsById("testGame"));
	}
	
	@Test
	@Transactional
	void testGetGame() {
		Game actual = gs.getGame("testGame");
		assertEquals(testGame, actual);
	}
	
	@Test
	@Transactional
	void testExists() {
		assertTrue(gs.exists("testGame"));
	}
	
	@Test
	@Transactional
	void testGetAll() {
		gr.save(testGame);
		gr.save(testGame2);
		List<Game> expected = new ArrayList<>();
		expected.add(testGame);
		expected.add(testGame2);
		assertEquals(expected, gs.getAllGames());
	}
	

}
