package com.lukashman.dao;

import java.util.Date;
import java.util.List;

import com.lukashman.model.Event;

public interface EventDAO {

	public List<Event> getAllEvents();
	
	public Event getEvent(int id);
	
	public void addEvent(Event event);
	
	public void updateEvent (Event event, String name, Date date, String eventType);
	
	public void deleteEvent(int id);
}
