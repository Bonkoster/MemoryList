package com.lukashman.starter;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lukashman.config.AppContext;
import com.lukashman.dao.EventDAOImpl;
import com.lukashman.model.Event;
import com.lukashman.model.EventType;

public class Start {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		EventDAOImpl daoImpl = ctx.getBean(EventDAOImpl.class);
		Event event = new Event("Master",new Date(),EventType.BIRTH_DAY);
		daoImpl.addEvent(event);
		List<Event> eventList = daoImpl.getAllEvents();
		eventList.forEach( e -> System.out.println(e.getId() + " " + e.getName()) );
	}
}
