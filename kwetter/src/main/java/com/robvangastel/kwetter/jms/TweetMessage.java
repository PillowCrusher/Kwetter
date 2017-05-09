package com.robvangastel.kwetter.jms;

import com.robvangastel.kwetter.domain.User;

import java.io.Serializable;

/**
 * Created by Rob on 29-4-2017.
 */

public class TweetMessage implements Serializable {
    private String content;
    private String username;

    public TweetMessage(User user, String content) {
        this.username = user.getUsername();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }
}