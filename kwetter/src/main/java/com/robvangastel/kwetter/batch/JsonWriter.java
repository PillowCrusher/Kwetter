package com.robvangastel.kwetter.batch;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.TweetException;
import com.robvangastel.kwetter.exception.UserException;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Rob on 7-4-2017.
 */
@Dependent
@Named("JsonWriter")
public class JsonWriter implements ItemWriter {

    @Inject
    ITweetDao tweetDao;

    @Inject
    IUserDao userDao;

    @Override
    public void writeItems(List list) throws Exception {
        User user;
        for (Object resultArray : list) {
            Tweet tweet = (Tweet) ((Object[]) resultArray)[0];
            String username = (String) ((Object[]) resultArray)[1];

            if (tweet != null || username != null) {
                user = userDao.findByUsername(username);
                if (user == null) {
                    user = new User(Role.USER, "batch@email.nl", "batch", "password");
                    userDao.create(user);
                }
                tweet.setUser(user);
                tweet = tweetDao.create(tweet);
                user.addTweet(tweet);
                userDao.update(user);
            }
        }
    }

    @Override
    public void open(Serializable serializable) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
