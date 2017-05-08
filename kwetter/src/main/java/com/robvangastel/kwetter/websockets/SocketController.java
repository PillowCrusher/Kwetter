package com.robvangastel.kwetter.websockets;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.UserService;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rob on 7-5-2017.
 */

@ServerEndpoint("/api/socket/{username}")
public class SocketController {

    static Map<User, ArrayList<Session>> peers = new HashMap<>();

    @Inject
    UserService userService;

    @OnOpen
    public void openConnection(@PathParam("username") String username, Session session) {
        User user = userService.findByUsername(username);
        addValues(user, session);
    }

    @OnClose
    public void closedConnection(Session session) {
//        for (User key : peers.keySet()) {
//            for (Session s : peers.get(key)) {
//                if (s == session) {
//                    ArrayList tempList = peers.get(key);
//                    tempList.remove(session);
//                    peers.put(key, tempList);
//                }
//            }
//        }
    }

    public static void send(Tweet tweet, List<User> users) {
        try {
            for (User key : peers.keySet()) {
                for (Session session : peers.get(key)) {
                    if(users.contains(key)) {
                        session.getBasicRemote().sendObject(tweet);
                    }
                }
            }
        } catch(Exception e) {

        }
    }

    private void addValues(User key, Session value) {
        ArrayList tempList = new ArrayList();
        if (peers.containsKey(key)) {
            tempList = peers.get(key);
            tempList.add(value);
        } else {
            tempList.add(value);
        }
        peers.put(key, tempList);
    }
}
