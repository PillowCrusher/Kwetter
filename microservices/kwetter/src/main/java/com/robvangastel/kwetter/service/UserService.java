package com.robvangastel.kwetter.service;

import com.robvangastel.kwetter.configuration.Utils;
import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Rob on 28-5-2017.
 */
public class UserService {

    @Autowired
    private IUserDao IUserDao;

    public User getUserbyUsername(String username) {
        return IUserDao.getUserbyUsername(username);
    }

    public void followUser(User follower, User followee) {
        IUserDao.insertFollower(follower, followee);
    }

    public void unfollowUser(User follower, User followee) {
        IUserDao.deleteFollower(follower, followee);
    }

    public void registerUser(User user) {
        user.setPassword(Utils.hashPassword(user.getPassword()));
        IUserDao.registerUser(user);
    }

    public boolean isUserFollower(User follower, User followee) {
        return IUserDao.isUserFollower(follower, followee);
    }
}
