package com.lukashman.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
		List<Event> eventList = jdbcTemplate.query("select * from Event_table", new RowMapper<Event>() {
			@Override
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Event event = new Event();
				event.setId(rs.getInt("id"));
				event.setName(rs.getString("name"));
				event.setEventDate(rs.getDate("event_date"));
				event.setEventType(rs.getString("event_type"));
				return event;
			}
		});
		return eventList;
	}

	@Override
	public Event getEvent(int id) {
		Logger.info("Getting event with id: " + id);
		Event event = jdbcTemplate.queryForObject("select * from event_table where id = ?", Event.class, id);
		return event;
	}

	@Override
	public void addEvent(Event event) {
		Logger.info("Adding new event");
		jdbcTemplate.update("insert into event_table(name,event_date,event_type) values(?,?,?)",event.getName(),event.getEventDate(),event.getEventType());
	}

	@Override
	public void deleteEvent(int id) {
		Logger.info("Deleting event with id: " + id);
		jdbcTemplate.update("delete from event_table where id = ?",id);
	}

}
