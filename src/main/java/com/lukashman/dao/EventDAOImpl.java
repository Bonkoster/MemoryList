package com.lukashman.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lukashman.dao.rowmappers.EventRowMapper;
import com.lukashman.model.Event;

public class EventDAOImpl implements EventDAO {

	protected final Log Logger = LogFactory.getLog(getClass());
	private JdbcTemplate jdbcTemplate;
	
	public EventDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Event> getAllEvents() {
		Logger.info("Getting all events");
		String sql = "select * from Event_table";
		List<Event> eventList = jdbcTemplate.query(sql, new EventRowMapper());
		return eventList;
	}

	@Override
	public Event getEvent(int id) {
		Logger.info("Getting event with id: " + id);
		String sql = "select * from Event_table where id = ?";
		Event event = jdbcTemplate.queryForObject(sql, new Object[] {id}, new EventRowMapper());
		return event;
	}

	@Override
	public void addEvent(Event event) {
		Logger.info("Adding new event");
		String sql = "insert into Event_table(name,eventDate,eventType) values(?,?,?)";
		jdbcTemplate.update(sql,event.getName(),event.getEventDate(),event.getEventType());
	}
	
	@Override
	public void updateEvent(Event event, String name, Date date, String eventType) {
		Logger.info("Updating existing event");
		String sql = "update Event_table set name = ?, eventDate = ?, eventType = ? where id = ?";
		jdbcTemplate.update(sql, name, date, eventType, event.getId());
	}

	@Override
	public void deleteEvent(int id) {
		Logger.info("Deleting event with id: " + id);
		String sql = "delete from Event_table where id = ?";
		jdbcTemplate.update(sql, id);
	}
}
