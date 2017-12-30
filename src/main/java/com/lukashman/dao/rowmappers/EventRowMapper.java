package com.lukashman.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lukashman.model.Event;

public class EventRowMapper implements RowMapper<Event> {

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		event.setId(rs.getInt("id"));
		event.setName(rs.getString("name"));
		event.setEventDate(rs.getDate("eventDate"));
		event.setEventType(rs.getString("eventType"));
		return event;
	}
}
