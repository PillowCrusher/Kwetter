/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;

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
    
    @Column(length = 140)
    private String message;
    private Date timeStamp;

    @OneToOne
    private User user;
    
    public Tweet(String message, Date timeStamp, User user) {

        this.message = message;
        this.timeStamp = timeStamp;
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
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Date timeStamp) {
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
}
