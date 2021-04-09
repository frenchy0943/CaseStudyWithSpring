package com.VideoGameTracker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.VideoGameTracker.entities.Game;
import com.VideoGameTracker.entities.User;
import com.VideoGameTracker.entities.UserGame;
import com.VideoGameTracker.entities.UserGameHours;
import com.VideoGameTracker.service.GameService;
import com.VideoGameTracker.service.UserGameService;
import com.VideoGameTracker.service.UserService;

@Controller
public class MainController {

	private String userName = "";

	@Autowired
	UserService us;

	@Autowired
	GameService gs;

	@Autowired
	UserGameService ugs;

	@RequestMapping("/")
	public String indexHandler() {
		return "login";
	}

	@RequestMapping("/register")
	public String registerHandler() {
		return "register";
	}

	@RequestMapping("/login")
	public String loginHandler() {
		return "login";
	}

	@RequestMapping("/profile")
	public ModelAndView profileHandler() {
		ModelAndView mav = new ModelAndView("profile");
		List<UserGame> userGames = ugs.getAllUserGamesById(userName);
		mav.addObject("profileListBean", userGames);
		return mav;
	}

	@RequestMapping("/addGame")
	public ModelAndView addGameHandler() {
		ModelAndView mav = new ModelAndView("addGame");
		List<Game> games = gs.getAllGames();
		mav.addObject("gameListBean", games);
		return mav;
	}

	@RequestMapping("/playGame")
	public ModelAndView playGameHandler() {
		ModelAndView mav = new ModelAndView("playGame");
		if (!userName.equals("")) {
			List<Game> games = us.getById(userName).getCurrentGames();
			mav.addObject("playListBean", games);
		}
		return mav;
	}

	@RequestMapping("/editGame")
	public ModelAndView editGameHandler() {
		ModelAndView mav = new ModelAndView("editGame");
		List<UserGame> games = ugs.getAllUserGamesById(userName);
		mav.addObject("editListBean", games);
		return mav;
	}

	@RequestMapping("/compare")
	public ModelAndView compareHandler() {
		ModelAndView mav = new ModelAndView("compare");
		List<UserGame> userGames = ugs.getAllUserGamesById(userName);
		mav.addObject("compareListBean", userGames);
		return mav;
	}

	@RequestMapping("/deleteGame")
	public ModelAndView deleteHandler() {
		ModelAndView mav = new ModelAndView("deleteGame");
		List<UserGame> userGames = ugs.getAllUserGamesById(userName);
		mav.addObject("deleteListBean", userGames);
		return mav;
	}

	@RequestMapping("/addNewGame")
	public String newGameHandler(@ModelAttribute UserGame ug, HttpServletRequest request) {
		if (!userName.equals("")) {
			ugs.linkUserAndGame(userName, ug.getGameName(), ug.getGameHours(), ug.getTimesCompleted(),
					ug.getCurrentList());
		}
		return "redirect:/addGame";
	}

	@RequestMapping("/editGameDetails")
	public String editGameDetailsHandler(@ModelAttribute UserGame ug) {
		if (!userName.equals("") && !ug.getGameName().equals("")) {
			double gameHours = 0.0;
			int timesCompleted = 0;
			if (ug.getGameHours() == null) {
				gameHours = ugs.getUserGame(userName, ug.getGameName()).getGameHours();
			} else {
				gameHours = ug.getGameHours();
			}

			if (ug.getTimesCompleted() == null) {
				timesCompleted = ugs.getUserGame(userName, ug.getGameName()).getTimesCompleted();
			} else {
				timesCompleted = ug.getTimesCompleted();
			}
			ugs.updateUserGame(userName, ug.getGameName(), gameHours, timesCompleted, ug.getCurrentList());
		}
		return "redirect:/editGame";
	}

	@RequestMapping(value = "/compareWithUsers", params = "hoursSort")
	public ModelAndView compareWithUsersHours(@ModelAttribute UserGame ug) {
		ModelAndView mav = compareHandler();
		if (!userName.equals("")) {
			List<UserGame> userGames = ugs.getAllByGameNameSortByHours(ug.getGameName());
			mav.addObject("nameOfGame", ug.getGameName());
			mav.addObject("compareGamesListBean", userGames);
		}
		return mav;
	}

	@RequestMapping(value = "/compareWithUsers", params = "completedSort")
	public ModelAndView compareWithUsersCompletions(@ModelAttribute UserGame ug) {
		ModelAndView mav = compareHandler();
		if (!userName.equals("")) {
			List<UserGame> userGames = ugs.getAllByGameNameSortByCompletions(ug.getGameName());
			mav.addObject("nameOfGame", ug.getGameName());
			mav.addObject("compareGamesListBean", userGames);
		}
		return mav;
	}

	@RequestMapping("/updateGameHours")
	public String updateGameHoursHandler(@ModelAttribute UserGameHours ugh) {
		if (!userName.equals("") && ugh.getGameName() != null) {
			UserGame ug = ugs.getUserGame(userName, ugh.getGameName());
			Double gameHours = ug.getGameHours() + ugh.getGameHours();
			gameHours *= 100;
			gameHours = (double) Math.round(gameHours) / 100;
			ugs.updateGameHours(ug.getUserName(), ug.getGameName(), gameHours);
		}
		return "redirect:/playGame";
	}

	@RequestMapping("/registerNewUser")
	public String registerNewUserHandler(@ModelAttribute User user, HttpServletRequest request) {
		if (us.registerUser(user.getUserName(), user.getPassword(), user.getPasswordVerification())) {
			return "redirect:/login";
		} else {
			if(user.getPassword().equals(user.getPasswordVerification())) {
				request.getSession().setAttribute("error", "Username is already in use");
			}else {
				request.getSession().setAttribute("error", "Passwords do not match");
			}
			return "redirect:/errorPage";
		}
	}

	@RequestMapping("/loginAttempt")
	public String loginAttemptHandler(@ModelAttribute User user, HttpServletRequest request) {
		if (us.validateUser(user.getUserName(), user.getPassword())) {
			this.userName = user.getUserName();
			request.getSession().setAttribute("userName", user.getUserName());
			return "redirect:/profile";
		}
		request.getSession().setAttribute("error", "Invalid Login Credentials. Please try again");
		return "redirect:/errorPage";
	}

	@RequestMapping("/errorPage")
	public String errorPageHandler() {
		return "error";
	}

	@RequestMapping("/removeGame")
	public String removeGameHandler(@ModelAttribute UserGame ug) {
		if (!ug.getGameName().equals("")) {
			ugs.removeGame(userName, ug.getGameName());
		}
		return "redirect:/deleteGame";
	}

	@RequestMapping("/logout")
	public String logoutHandler(HttpServletRequest request) {
		request.getSession().setAttribute("userName", null);
		this.userName = "";
		return "redirect:/login";
	}

}
