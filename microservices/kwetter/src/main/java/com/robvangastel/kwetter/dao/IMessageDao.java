package com.robvangastel.kwetter.dao;

import java.util.List;

import com.robvangastel.kwetter.domain.Message;
import com.robvangastel.kwetter.domain.User;

public interface IMessageDao {
	List<Message> getUserTimelineMessages(User user);
	
	List<Message> getUserFullTimelineMessages(User user);
	
	List<Message> getPublicTimelineMessages();
	
	void insertMessage(Message m);
}
