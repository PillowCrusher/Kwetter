package com.robvangastel.kwetter.service;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.domain.Message;
import com.robvangastel.kwetter.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Rob on 28-5-2017.
 */

@Service
public class TweetService {

    @Autowired
    private ITweetDao ITweetDao;

    public List<Message> getUserFullTimelineMessages(User user) {
        return ITweetDao.getUserFullTimelineMessages(user);
    }

    public List<Message> getUserTimelineMessages(User user) {
        return ITweetDao.getUserTimelineMessages(user);
    }

    public List<Message> getPublicTimelineMessages() {
        return ITweetDao.getPublicTimelineMessages();
    }

    public void addMessage(Message message) {
        ITweetDao.create(message);
    }
}
