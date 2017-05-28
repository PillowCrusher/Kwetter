package com.robvangastel.kwetter.configuration;

import com.robvangastel.kwetter.domain.User;

public class Login {
	
	private String error;
	private User user;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
