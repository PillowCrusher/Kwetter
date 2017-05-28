package com.robvangastel.kwetter.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.robvangastel.kwetter.dao.IMessageDao;
import com.robvangastel.kwetter.domain.Message;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.configuration.Utils;

@Repository
public class IMessageDaoImpl implements IMessageDao {

	private NamedParameterJdbcTemplate template;

	@Autowired
	public IMessageDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public List<Message> getUserTimelineMessages(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user.getId());
        
		String sql = "select message.*, user.* from message, user where " +
				"user.user_id = message.author_id and user.user_id = :id " +
				"order by message.pub_date desc";
		List<Message> result = template.query(sql, params, messageMapper);
		
		return result;
	}

	@Override
	public List<Message> getUserFullTimelineMessages(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user.getId());
        
		String sql = "select message.*, user.* from message, user " +
				"where message.author_id = user.user_id and ( " +
				"user.user_id = :id or " +
				"user.user_id in (select followee_id from follower " +
                                    "where follower_id = :id))" +
                "order by message.pub_date desc";
		List<Message> result = template.query(sql, params, messageMapper);
		
		return result;
	}

	@Override
	public List<Message> getPublicTimelineMessages() {
		Map<String, Object> params = new HashMap<String, Object>();
        
		String sql = "select message.*, user.* from message, user " +
				"where message.author_id = user.user_id " +
				"order by message.pub_date desc";
		List<Message> result = template.query(sql, params, messageMapper);
		
		return result;
	}

	@Override
	public void insertMessage(Message m) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", m.getUserId());
        params.put("text", m.getText());
        params.put("pubDate", m.getPubDate());
        
        String sql = "insert into message (author_id, text, pub_date) values (:userId, :text, :pubDate)";
		template.update(sql, params);
	}
	
	private RowMapper<Message> messageMapper = (rs, rowNum) -> {
		Message m = new Message();
		
		m.setId(rs.getInt("message_id"));
		m.setUserId(rs.getInt("author_id"));
		m.setUsername(rs.getString("username"));
		m.setText(rs.getString("text"));
		m.setPubDate(rs.getTimestamp("pub_date"));
		m.setGravatar(Utils.gravatarURL(rs.getString("email"), "identicon", 48));
		
		return m;
	};

}
