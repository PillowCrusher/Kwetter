package com.robvangastel.kwetter.dao;

import com.robvangastel.kwetter.domain.User;

public interface IUserDao {

	User getUserbyUsername(String username);
	
	void insertFollower(User follower, User followee);
	
	void deleteFollower(User follower, User followee);
	
	boolean isUserFollower(User follower, User followee);
	
	void registerUser(User user);
}
