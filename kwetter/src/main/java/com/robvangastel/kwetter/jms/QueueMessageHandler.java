package com.robvangastel.kwetter.jms;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by Rob on 29-4-2017.
 */

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/tweets/queue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class QueueMessageHandler implements MessageListener {

    @Inject
    private TweetService service;

    @Inject
    private UserService userService;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        TweetMessage tweetMessage;

        try {
            tweetMessage = (TweetMessage) objectMessage.getObject();
            service.create(new Tweet(tweetMessage.getContent(), userService.findByUsername(tweetMessage.getUsername())));

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}