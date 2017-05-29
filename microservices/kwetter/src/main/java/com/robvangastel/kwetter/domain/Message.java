package com.robvangastel.kwetter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

	private int id;
	private int userId;
	private String username;
	private String text;
	private Date pubDate;
	private String pubDateStr;
	private String avatar;

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
		if(pubDate != null) {
			pubDateStr = new SimpleDateFormat("yyyy-MM-dd @ HH:mm").format(pubDate);
		}
	}
}
