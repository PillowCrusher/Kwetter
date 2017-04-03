/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.*;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Rob
 */


@Entity
public class Tweet implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(length = 140, nullable = false)
    private String message;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	@Column(nullable = false)
    private Timestamp timeStamp;

    @JsonManagedReference
    @ManyToOne
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	private List<String> hashtags = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	private List<String> mentions = new ArrayList<>();
    
    public Tweet(String message, User user) {
	    Matcher hashtagMatches = Pattern.compile("\\B#\\w\\w+").matcher(message);
	    while (hashtagMatches.find()) {
		    hashtags.add(hashtagMatches.group());
	    }
	    this.setHashtags(hashtags);

	    Matcher mentionMatches = Pattern.compile("\\B@\\w\\w+").matcher(message);
	    while (mentionMatches.find()) {
		    mentions.add(mentionMatches.group());
	    }
	    this.setMentions(mentions);

        this.message = message;
        this.timeStamp = new Timestamp(System.currentTimeMillis());
        this.user = user;
    }
    
    public Tweet() {}

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

	/**
	 * Deserializes an Object of class Tweet from its JSON representation
	 * @param jsonRepresentation JSON String
	 * @return User object or null when an error occurs
	 */
	public static Tweet fromString(String jsonRepresentation) {
		ObjectMapper mapper = new ObjectMapper(); //Jackson's JSON marshaller
		Tweet o = null;
		try {
			o = mapper.readValue(jsonRepresentation, Tweet.class );
		} catch (IOException e) {
			throw new WebApplicationException();
		}
		return o;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

	public List<String> getMentions() {
		return mentions;
	}

	public void setMentions(List<String> mentions) {
		this.mentions = mentions;
	}
}
