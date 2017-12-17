package com.lukashman.model;

import java.util.Date;

public class Event {
	
	private int id;
	private String name;
	private Date eventDate;
	private String eventType;
	
	public Event() {
	}
	
	public Event(String name, Date eventDate, String eventType) {
		this.name = name;
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
