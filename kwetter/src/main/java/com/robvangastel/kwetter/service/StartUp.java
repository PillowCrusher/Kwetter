package com.robvangastel.kwetter.service;

import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by robvangastel on 09/03/2017.
 */

@Singleton
@Startup
public class StartUp {

	@Inject
	private UserService userService;

	@PostConstruct
	public void initData() {
		try {
			userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
			userService.create(new User(Role.ADMINISTRATOR, "user@admin.com", "admin", "password"));
			userService.create(new User(Role.MODERATOR, "user@moderator.com", "moderator", "password"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
