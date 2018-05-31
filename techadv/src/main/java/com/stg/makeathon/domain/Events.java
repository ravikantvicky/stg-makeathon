package com.stg.makeathon.domain;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "events")
public class Events {
	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private int eventId;
	@JsonIgnore
	private String keywords;
	@JsonProperty("eventName")
	private String name;
	private String description;
	@Column(name = "event_date")
	private Date eventDate;
	@Column(name = "event_time")
	private Time eventTime;
	@JsonProperty("pricing")
	@Column(name = "joining_fee")
	private String joiningFee;
	@JsonProperty("actualUrl")
	@Column(name = "event_url")
	private String eventUrl;
	@Column(name = "img_key")
	private String imgKey;
	private String location;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Time getEventTime() {
		return eventTime;
	}

	public void setEventTime(Time eventTime) {
		this.eventTime = eventTime;
	}

	public String getJoiningFee() {
		return joiningFee;
	}

	public void setJoiningFee(String joiningFee) {
		this.joiningFee = joiningFee;
	}

	public String getEventUrl() {
		return eventUrl;
	}

	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}

	public String getImgKey() {
		return imgKey;
	}

	public void setImgKey(String imgKey) {
		this.imgKey = imgKey;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
