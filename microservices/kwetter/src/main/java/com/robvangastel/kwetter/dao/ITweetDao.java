package com.robvangastel.kwetter.dao;

import java.util.List;

import com.robvangastel.kwetter.domain.Message;
import com.robvangastel.kwetter.domain.User;

public interface ITweetDao {

	/***
	 *
	 * @param user
	 * @return
     */
	List<Message> getUserTimelineMessages(User user);

	/***
	 *
	 * @param user
	 * @return
     */
	List<Message> getUserFullTimelineMessages(User user);

	/***
	 *
	 * @return
     */
	List<Message> getPublicTimelineMessages();

	/***
	 * Create message
	 * @param m
     */
	void create(Message m);
}
