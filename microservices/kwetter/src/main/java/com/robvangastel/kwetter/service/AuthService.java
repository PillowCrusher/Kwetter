package com.robvangastel.kwetter.service;

import java.util.List;

import com.robvangastel.kwetter.configuration.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.configuration.Login;
import com.robvangastel.kwetter.domain.Message;
import com.robvangastel.kwetter.domain.User;

@Service
public class AuthService {
	
	@Autowired
	private IUserDao IUserDao;
	
	public Login authenticate(User user) {
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
}
