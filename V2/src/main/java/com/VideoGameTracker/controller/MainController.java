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

/**
 * Controller class for the application
 * @author Brandon French
 *
 */
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
	
	/**
	 * Create bean with all UserGames for a user, add it to ModelAndView and return
	 * @return ModelAndView with "profile" page
	 */
	@RequestMapping("/profile")
	public ModelAndView profileHandler() {
		ModelAndView mav = new ModelAndView("profile");
		List<UserGame> userGames = ugs.getAllUserGamesById(userName);
		mav.addObject("profileListBean", userGames);
		return mav;
	}

	/**
	 * create Bean with all games in database, add it to ModelAndView and return
	 * @return ModelAndView with "addGame" page
	 */
	@RequestMapping("/addGame")
	public ModelAndView addGameHandler() {
		ModelAndView mav = new ModelAndView("addGame");
		List<Game> games = gs.getAllGames();
		mav.addObject("gameListBean", games);
		return mav;
	}

	/**
	 * Create bean with all games in the currently playing list for current user, add it to ModelAndView and return
	 * @return ModelAndView with "playGame" page
	 */
	@RequestMapping("/playGame")
	public ModelAndView playGameHandler() {
		ModelAndView mav = new ModelAndView("playGame");
		if (!userName.equals("")) {
			List<Game> games = us.getById(userName).getCurrentGames();
			mav.addObject("playListBean", games);
		}
		return mav;
	}

	/**
	 * Create bean with all UserGames for current user, add it to ModelAndView and return
	 * @return ModelAndView with "editGame" page
	 */
	@RequestMapping("/editGame")
	public ModelAndView editGameHandler() {
		ModelAndView mav = new ModelAndView("editGame");
		List<UserGame> games = ugs.getAllUserGamesById(userName);
		mav.addObject("editListBean", games);
		return mav;
	}

	/**
	 * Create bean with all UserGame for current user, add it to ModelAndView and return
	 * @return ModelAndView with "compare" page
	 */
	@RequestMapping("/compare")
	public ModelAndView compareHandler() {
		ModelAndView mav = new ModelAndView("compare");
		List<UserGame> userGames = ugs.getAllUserGamesById(userName);
		mav.addObject("compareListBean", userGames);
		return mav;
	}

	/**
	 * Create bean with all UserGames for current user, add it to ModelAndView and return
	 * @return ModelAndView with "deleteGame" page
	 */
	@RequestMapping("/deleteGame")
	public ModelAndView deleteHandler() {
		ModelAndView mav = new ModelAndView("deleteGame");
		List<UserGame> userGames = ugs.getAllUserGamesById(userName);
		mav.addObject("deleteListBean", userGames);
		return mav;
	}

	/**
	 * When user clicks add game button, call the correct service method and redirect back to addGame page
	 * @param ug ModelAttribute coming in from JSP
	 * @param request use to get access to session attributes
	 * @return redirect to addGame
	 */
	@RequestMapping("/addNewGame")
	public String newGameHandler(@ModelAttribute UserGame ug, HttpServletRequest request) {
		if (!userName.equals("")) {
			ugs.linkUserAndGame(userName, ug.getGameName(), ug.getGameHours(), ug.getTimesCompleted(),
					ug.getCurrentList());
		}
		return "redirect:/addGame";
	}

	/**
	 * When user clicks the update game button, call the correct service method and return a redirect to editGame
	 * @param ug ModelAttribute coming in from the JSP
	 * @return redirect to editGame
	 */
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

	/**
	 * When click the compare by hours button, call the correct service method to get the correct list
	 * Add that list to the ModelAndView returned by compareHandler() and return it
	 * @param ug ModelAttribute coming in from the JSP
	 * @return ModelAndView that comes from compareHandler()
	 */
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

	/**
	 * When click the compare by times completed button, call the correct service method to get the correct list
	 * Add that list to the ModelAndView returned by compareHandler() and return it
	 * @param ug ModelAttribute coming in from the JSP
	 * @return ModelAndView that comes from compareHandler()
	 */
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
	
	/**
	 * When users are sorting their own profile and sort by hours, call the correct sevice method to get the correct list
	 * Add that list to the ModelAndView returned by profileHandler() and return it
	 * @return The ModelAndView that comes from the profileHandler()
	 */
	@RequestMapping(value = "/sortProfile", params = "hoursSort")
	public ModelAndView sortProfileHoursHandler() {
		ModelAndView mav = profileHandler();
		if(!userName.equals("")) {
			List<UserGame> userGames = ugs.getAllByIdHoursSort(userName);
			mav.addObject("profileListBean", userGames);
		}
		return mav;
	}
	
	/**
	 * When users are sorting their own profile and sort by times completed, call the correct sevice method to get the correct list
	 * Add that list to the ModelAndView returned by profileHandler() and return it
	 * @return The ModelAndView that comes from the profileHandler()
	 */
	@RequestMapping(value = "/sortProfile", params = "completedSort")
	public ModelAndView sortProfileCompletionsHandler() {
		ModelAndView mav = profileHandler();
		if(!userName.equals("")) {
			List<UserGame> userGames = ugs.getAllByIdCompletionsSort(userName);
			mav.addObject("profileListBean", userGames);
		}
		return mav;
	}
	
	/**
	 * When users are sorting their own profile and sort by list, call the correct sevice method to get the correct list
	 * Add that list to the ModelAndView returned by profileHandler() and return it
	 * @return The ModelAndView that comes from the profileHandler()
	 */
	@RequestMapping(value = "/sortProfile", params = "listSort")
	public ModelAndView sortProfileListHandler() {
		ModelAndView mav = profileHandler();
		if(!userName.equals("")) {
			List<UserGame> userGames = ugs.getAllByIdListSort(userName);
			mav.addObject("profileListBean", userGames);
		}
		return mav;
	}

	/**
	 * When the user clicks the update button on the play game page to add hours to a game. Take the number coming from the JSP round it
	 * and send it to the correct method.
	 * @param ugh ModelAttribute coming from the JSP
	 * @return redirect to playGame page
	 */
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

	/**
	 * When a user is attempting to register for an account, call the correct service method, and set error message depending on response
	 * @param user ModelAttribute coming in from the JSP
	 * @param request to have access to the session attributes
	 * @return redirect to login if valid, error otherwise
	 */
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

	/**
	 * When a user is attempting to login, call the correct service method, and return a redirect to profile or error
	 * @param user ModelAttribute coming in from JSP
	 * @param request to have access to session attributes
	 * @return redirect to profile if valid, error otherwise
	 */
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

	/**
	 * When a user is trying to remove a game from their profile, call the correct service method and redirect
	 * @param ug ModelAttribute coming in from the JSP
	 * @return redirect to deleteGame
	 */
	@RequestMapping("/removeGame")
	public String removeGameHandler(@ModelAttribute UserGame ug) {
		if (!ug.getGameName().equals("")) {
			ugs.removeGame(userName, ug.getGameName());
		}
		return "redirect:/deleteGame";
	}

	/**
	 * When a user logs out, clear the reference to the username in controller class, and set session attribute to null
	 * @param request to have access to session attributes
	 * @return redirect to login
	 */
	@RequestMapping("/logout")
	public String logoutHandler(HttpServletRequest request) {
		request.getSession().setAttribute("userName", null);
		this.userName = "";
		return "redirect:/login";
	}

}
