package com.lukashman.model;

import java.util.Date;

public class Event {
	
	private String name;
	private Date eventDate;
	private String eventType;
	
	public Event() {
		super();
	}
	public Event(String name, Date eventDate, String eventType) {
		super();
		this.name = name;
		this.eventDate = eventDate;
		this.eventType = eventType;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
