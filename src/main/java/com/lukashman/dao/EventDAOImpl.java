package com.lukashman.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lukashman.config.ApplicationContext;
import com.lukashman.model.Event;

public class EventDAOImpl implements EventDAO {

	
	protected final Log Logger = LogFactory.getLog(getClass());
	private List<Event> eventList;
	
	@Override
	public List<Event> getAllEvents() {
		Logger.info("Getting all events");
		
		return null;
	}

	@Override
	public Event getEvent(int id) {
		Logger.info("Getting event with id: " + id);
		
		return null;
	}

	@Override
	public void addEvent(Event event) {
		Logger.info("Adding new event");
		
	}

	@Override
	public void deleteEvent(int id) {
		Logger.info("Deleting event with id: " + id);
		
	}

}
