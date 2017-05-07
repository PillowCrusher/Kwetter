package com.robvangastel.kwetter.websockets;

import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rob on 7-5-2017.
 */

@ServerEndpoint("/api/socket/{username}")
public class SocketController {
    static Map<String, ArrayList<Session>> peers = new HashMap<>();

    private Session wsSession;

    @Inject
    UserService userService;

    @OnOpen
    public void openConnection(@PathParam("username") String username, Session session) {
        User user = userService.findByUsername(username);

        addValues(user.getUsername(), session);
        this.wsSession = session;

        send("New session started "
                + this.wsSession.getId()
                + " by user with username: "
                + session.getUserPrincipal().getName());
    }

    @OnClose
    public void closedConnection(Session session) {
        for (String key : peers.keySet()) {
            for (Session s : peers.get(key)) {
                if (s == session) {
                    ArrayList tempList = peers.get(key);
                    tempList.remove(session);
                    peers.put(key, tempList);
                }
            }
        }
    }

    public static void send(String msg) {
        try {
            for (String key : peers.keySet()) {
                for (Session session : peers.get(key)) {
                    session.getBasicRemote().sendObject(msg);
                }
            }
        } catch(Exception e) {

        }
    }

    private void addValues(String key, Session value) {
        ArrayList tempList;
        if (peers.containsKey(key)) {
            tempList = peers.get(key);
            if (tempList == null) {
                tempList = new ArrayList();
            }
            tempList.add(value);
        } else {
            tempList = new ArrayList();
            tempList.add(value);
        }
        peers.put(key, tempList);
    }
}
