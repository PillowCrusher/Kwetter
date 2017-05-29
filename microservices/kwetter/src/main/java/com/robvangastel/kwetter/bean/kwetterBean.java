package com.robvangastel.kwetter.bean;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robvangastel.kwetter.configuration.Login;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import com.robvangastel.kwetter.domain.Message;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.AuthService;

import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;
import spark.utils.StringUtils;

public class kwetterBean {

	private AuthService authService;

	private UserService userService;

	private TweetService tweetService;

	public kwetterBean(UserService userService, TweetService tweetService, AuthService authService) {
		this.authService = authService;
		this.userService = userService;
		this.tweetService = tweetService;

		staticFileLocation("/public");
		setupRoutes();
	}
	
	private void setupRoutes() {

		get("/", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Timeline");
			map.put("user", user);
			List<Message> messages = tweetService.getUserFullTimelineMessages(user);
			map.put("messages", messages);
			return new ModelAndView(map, "timeline.ftl");
        }, new FreeMarkerEngine());
		before("/", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if(user == null) {
				res.redirect("/public");
				halt();
			}
		});

		get("/public", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Global Timeline");
			map.put("user", user);
			List<Message> messages = tweetService.getPublicTimelineMessages();
			map.put("messages", messages);
			return new ModelAndView(map, "timeline.ftl");
        }, new FreeMarkerEngine());

		get("/t/:username", (req, res) -> {
			String username = req.params(":username");
			User profileUser = userService.getUserbyUsername(username);
			
			User authUser = getAuthenticatedUser(req);
			boolean followed = false;
			if(authUser != null) {
				followed = userService.isUserFollower(authUser, profileUser);
			}
			List<Message> messages = tweetService.getUserTimelineMessages(profileUser);
			
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", username + "'s Timeline");
			map.put("user", authUser);
			map.put("profileUser", profileUser);
			map.put("followed", followed);
			map.put("messages", messages);
			return new ModelAndView(map, "timeline.ftl");
        }, new FreeMarkerEngine());

		before("/t/:username", (req, res) -> {
			String username = req.params(":username");
			User profileUser = userService.getUserbyUsername(username);
			if(profileUser == null) {
				halt(404, "User not Found");
			}
		});

		get("/t/:username/follow", (req, res) -> {
			String username = req.params(":username");
			User profileUser = userService.getUserbyUsername(username);
			User authUser = getAuthenticatedUser(req);

			userService.followUser(authUser, profileUser);
			res.redirect("/t/" + username);
			return null;
        });

		before("/t/:username/follow", (req, res) -> {
			String username = req.params(":username");
			User authUser = getAuthenticatedUser(req);
			User profileUser = userService.getUserbyUsername(username);
			if(authUser == null) {
				res.redirect("/login");
				halt();
			} else if(profileUser == null) {
				halt(404, "User not Found");
			}
		});

		get("/t/:username/unfollow", (req, res) -> {
			String username = req.params(":username");
			User profileUser = userService.getUserbyUsername(username);
			User authUser = getAuthenticatedUser(req);

			userService.unfollowUser(authUser, profileUser);
			res.redirect("/t/" + username);
			return null;
        });

		before("/t/:username/unfollow", (req, res) -> {
			String username = req.params(":username");
			User authUser = getAuthenticatedUser(req);
			User profileUser = userService.getUserbyUsername(username);
			if(authUser == null) {
				res.redirect("/login");
				halt();
			} else if(profileUser == null) {
				halt(404, "User not Found");
			}
		});

		get("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			if(req.queryParams("r") != null) {
				map.put("message", "You were successfully registered and can login now");
			}
			return new ModelAndView(map, "login.ftl");
        }, new FreeMarkerEngine());

		post("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			User user = new User();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(user, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}
			Login result = authService.authenticate(user);
			if(result.getUser() != null) {
				addAuthenticatedUser(req, result.getUser());
				res.redirect("/");
				halt();
			} else {
				map.put("error", result.getError());
			}
			map.put("username", user.getUsername());
			return new ModelAndView(map, "login.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Checks if the user is already authenticated
		 */
		before("/login", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if(authUser != null) {
				res.redirect("/");
				halt();
			}
		});
		
		
		/*
		 * Presents the register form or redirect the user to
		 * her timeline if it's already logged in
		 */
		get("/register", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			return new ModelAndView(map, "register.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Registers the user.
		 */
		post("/register", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			User user = new User();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(user, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}
			String error = user.validate();
			if(StringUtils.isEmpty(error)) {
				User existingUser = userService.getUserbyUsername(user.getUsername());
				if(existingUser == null) {
					userService.registerUser(user);
					res.redirect("/login?r=1");
					halt();
				} else {
					error = "The username is already taken";
				}
			}
			map.put("error", error);
			map.put("username", user.getUsername());
			map.put("email", user.getEmail());
			return new ModelAndView(map, "register.ftl");
        }, new FreeMarkerEngine());
		/*
		 * Checks if the user is already authenticated
		 */
		before("/register", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if(authUser != null) {
				res.redirect("/");
				halt();
			}
		});
		
		
		/*
		 * Registers a new message for the user.
		 */
		post("/message", (req, res) -> {
			User user = getAuthenticatedUser(req);
			MultiMap<String> params = new MultiMap<String>();
			UrlEncoded.decodeTo(req.body(), params, "UTF-8");
			Message m = new Message();
			m.setUserId(user.getId());
			m.setPubDate(new Date());
			BeanUtils.populate(m, params);
			tweetService.addMessage(m);
			res.redirect("/");
			return null;
        });
		/*
		 * Checks if the user is authenticated
		 */
		before("/message", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if(authUser == null) {
				res.redirect("/login");
				halt();
			}
		});
		
		
		/*
		 * Logs the user out and redirects to the public timeline
		 */
		get("/logout", (req, res) -> {
			removeAuthenticatedUser(req);
			res.redirect("/public");
			return null;
        });
	}

	private void addAuthenticatedUser(Request request, User u) {
		request.session().attribute("user", u);
		
	}

	private void removeAuthenticatedUser(Request request) {
		request.session().removeAttribute("user");
		
	}

	private User getAuthenticatedUser(Request request) {
		return request.session().attribute("user");
	}
}
