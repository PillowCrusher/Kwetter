package com.robvangastel.kwetter.service;

import java.util.List;

import com.robvangastel.kwetter.configuration.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robvangastel.kwetter.dao.IMessageDao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.configuration.Login;
import com.robvangastel.kwetter.domain.Message;
import com.robvangastel.kwetter.domain.User;

@Service
public class KwetterService {
	
	@Autowired
	private IUserDao IUserDao;
	
	@Autowired
	private IMessageDao IMessageDao;
	
	public List<Message> getUserFullTimelineMessages(User user) {
		return IMessageDao.getUserFullTimelineMessages(user);
	}
	
	public List<Message> getUserTimelineMessages(User user) {
		return IMessageDao.getUserTimelineMessages(user);
	}
	
	public List<Message> getPublicTimelineMessages() {
		return IMessageDao.getPublicTimelineMessages();
	}
	
	public User getUserbyUsername(String username) {
		return IUserDao.getUserbyUsername(username);
	}
	
	public void followUser(User follower, User followee) {
		IUserDao.insertFollower(follower, followee);
	}
	
	public void unfollowUser(User follower, User followee) {
		IUserDao.deleteFollower(follower, followee);
	}
	
	public boolean isUserFollower(User follower, User followee) {
		return IUserDao.isUserFollower(follower, followee);
	}
	
	public void addMessage(Message message) {
		IMessageDao.insertMessage(message);
	}
	
	public Login checkUser(User user) {
		Login result = new Login();
		User userFound = IUserDao.getUserbyUsername(user.getUsername());
		if(userFound == null) {
			result.setError("Invalid username");
		} else if(!Utils.verifyPassword(user.getPassword(), userFound.getPassword())) {
			result.setError("Invalid password");
		} else {
			result.setUser(userFound);
		}
		
		return result;
	}
	
	public void registerUser(User user) {
		user.setPassword(Utils.hashPassword(user.getPassword()));
		IUserDao.registerUser(user);
	}
}
