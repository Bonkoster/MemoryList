package com.lukashman.testcase;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lukashman.config.AppContext;
import com.lukashman.dao.EventDAO;
import com.lukashman.dao.EventDAOImpl;
import com.lukashman.model.Event;

public class MemoryListTestCase {

	ApplicationContext appctx;
	EventDAO eventDAO;
	
	@BeforeAll
	public void init() {
	}
	
	@Test
	public void dataBaseTest() {
		appctx = new AnnotationConfigApplicationContext(AppContext.class);
		eventDAO = appctx.getBean(EventDAOImpl.class);
		List<Event> events = eventDAO.getAllEvents();
		Assertions.assertNotNull(events);
		fail("The list is empty");
	}
}
