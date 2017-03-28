package com.robvangastel.kwetter.service;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.dao.JPA;
import com.robvangastel.kwetter.dao.facade.TweetDaoJPAImpl;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 * Created by robvangastel on 09/03/2017.
 */

@Singleton
@Startup
public class StartUp {

	@Inject
	private UserService userService;

	@Inject @JPA
	private ITweetDao dao;

	@PostConstruct
	public void initData() {
		User u = new User(Role.USER, "user@mail.com", "user", "password");

		try {
			userService.create(u);
			userService.create(new User(Role.ADMINISTRATOR, "user@admin.com", "admin", "password"));
			userService.create(new User(Role.MODERATOR, "user@moderator.com", "moderator", "password"));

			dao.create(new Tweet("Kwetter #cool #swag", u));

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
