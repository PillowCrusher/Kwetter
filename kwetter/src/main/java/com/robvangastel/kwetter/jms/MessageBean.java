//package com.robvangastel.kwetter.jms;
//
//import com.robvangastel.kwetter.domain.Tweet;
//import com.robvangastel.kwetter.domain.User;
//import com.robvangastel.kwetter.service.TweetService;
//import com.robvangastel.kwetter.service.UserService;
//import org.jboss.logging.Logger;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.inject.Inject;
//
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import java.util.logging.Level;
//
///**
// * Created by Rob on 29-4-2017.
// */
//
//@MessageDriven(activationConfig = {
//        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
//        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/KwetterGo/topic"),
//        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
//        @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/KwetterGo/topic"),
//        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/KwetterGo/topic")
//})
//public class MessageBean implements MessageListener {
//
//    @Inject
//    TweetService tweetService;
//
//    @Inject
//    UserService userService;
//
//    @Override
//    public void onMessage(Message message) {
//        try {
//            String messageString = message.getBody(String.class);
//            System.out.println("Got a message:");
//            System.out.println(messageString);
//
//            String messageText = messageString.split(" --- ")[0];
//            String messageUsername = messageString.split(" --- ")[1];
//
//            User user = userService.findByUsername(messageUsername);
//            if (user == null) {
//                System.out.println("Can't find user with name " + messageUsername);
//                return;
//            }
//            user.addTweet(new Tweet(messageText, user));
//            userService.update(user);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//}