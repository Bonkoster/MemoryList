package com.lukashman.model;

import java.util.Date;

public class Event {
	
	private int id;
	private String eventName;
	private Date eventDate;
	private String eventType;
	
	public Event() {
	}
	
	public Event(String eventName, Date eventDate, String eventType) {
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventType = eventType;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return eventName;
	}
	
	public void setName(String eventName) {
		this.eventName = eventName;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	public String getEventType() {
		return eventType;
	}
	
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
