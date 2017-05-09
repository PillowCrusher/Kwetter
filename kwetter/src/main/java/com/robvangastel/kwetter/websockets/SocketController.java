package com.robvangastel.kwetter.websockets;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.UserService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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

@ServerEndpoint( value = "/api/socket/{username}")
public class SocketController {

    static Map<Long, ArrayList<Session>> peers = new HashMap<>();

    @Inject
    UserService userService;

    @OnOpen
    public void openConnection(@PathParam("username") String username, Session session) {
        User user = userService.findByUsername(username);
        addValues(user.getId(), session);
    }

    @OnClose
    public static void closedConnection(Session session) {
        for (Long key : peers.keySet()) {
            for (Session s : peers.get(key)) {
                if (s == session) {
                    ArrayList tempList = peers.get(key);
                    tempList.remove(session);
                    peers.put(key, tempList);
                }
            }
        }
    }

    public static void send(Tweet tweet, List<User> users) {
        try {
            for (Long key : peers.keySet()) {
                for (Session session : peers.get(key)) {
                    for(User u : users) {
                        if(u.getId() == key) {
                            if(session.isOpen()) {
                                JsonObject object = Json.createObjectBuilder().
                                        add("user_id", tweet.getUser().getId()).
                                        add("message", tweet.getMessage()).
                                        add("timestamp", tweet.getTimeStamp().toString()).
                                        add("username", tweet.getUser().getUsername())
                                        .build();

                                session.getBasicRemote().sendObject(object.toString());
                            } else {
                                closedConnection(session);
                            }
                        }
                    }
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addValues(Long key, Session value) {
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
