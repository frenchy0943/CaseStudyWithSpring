package com.VideoGameTracker.V2;

import static org.junit.jupiter.api.Assertions.*;

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
import com.VideoGameTracker.repo.UserRepository;
import com.VideoGameTracker.service.UserService;

@SpringBootTest
class UserTest {
	
	@Autowired
	UserService us;
	
	@Autowired
	UserRepository ur;
	
	User testUser;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testUser = new User("testUser", "Testpass1", "Testpass1");
		ur.save(testUser);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	@Transactional
	void testAdd() {
		us.addUser(new User("Brandon", "Password1", "Password1"));
		assertTrue(ur.existsById("Brandon"));
	}
	
	@Test
	@Transactional
	void testGetById() {
		User actual = us.getById("testUser");
		assertEquals(testUser, actual);
	}
	
	@Test
	@Transactional
	void testValidate() {
		assertTrue(us.validateUser("testUser", "Testpass1"));
		assertFalse(us.validateUser("testUser", "testPass1"));
	}
	
	@Test
	@Transactional
	void testRegister() {
		assertTrue(us.registerUser("testUser2", "Password1", "Password1"));
		assertFalse(us.registerUser("testUser2", "Password1", "Password1"));
	}
	
	@Test
	@Transactional
	void testAddToList() {
		assertEquals(1, us.addToList("testUser", new Game("Nioh 2"), "current"));
		assertEquals(0, us.addToList("testUser", new Game("Nioh 2"), "currentList"));

	}
	
	@Test
	@Transactional
	void testRemoveFromList() {
		assertEquals(1, us.removeFromList("testUser", new Game("Nioh 2"), "current"));
		assertEquals(0, us.removeFromList("testUser", new Game("Nioh 2"), "currentList"));
	}

}
