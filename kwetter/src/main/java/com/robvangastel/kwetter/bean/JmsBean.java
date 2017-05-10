package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.dao.JPA;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.TweetException;
import com.robvangastel.kwetter.exception.UserException;
import com.robvangastel.kwetter.service.TweetService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by robvangastel on 10/05/2017.
 */

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/kwetter")
})
public class JmsBean implements MessageListener {

    @Inject @JPA
    private IUserDao userDao;

    @Inject
    private TweetService tweetService;


    public void onMessage(Message m) {
        try {
            String message = ((TextMessage)m).getText();
            String[] results = message.split(":", 2);
            String username =  results[0];

            User user = userDao.findByUsername(username);

            if(user == null) {
                return;
            }

            String tweet = results[1];
            tweetService.create(new Tweet(tweet, user));
            System.out.println("New Tweet Added: " + username + ": " + tweet);

        } catch (JMSException | TweetException | UserException e) {
            e.printStackTrace();
        }
    }
}