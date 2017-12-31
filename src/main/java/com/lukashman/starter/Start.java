package com.lukashman.starter;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lukashman.config.AppContext;
import com.lukashman.dao.EventDAO;
import com.lukashman.dao.EventDAOImpl;
import com.lukashman.gui.EventMenu;
import com.lukashman.gui.EventShow;
import com.lukashman.model.Event;

import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {
	
	private static String[] savedArgs;
	
	public static void main(String[] args) {
		savedArgs = args;
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		if (savedArgs.length == 0) {
			ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
			EventDAO dao = ctx.getBean(EventDAOImpl.class);
			List<Event> eventList = dao.getAllEvents();
			Date date = new Date();
			
			for (Event event : eventList) {
				Date eventDate = event.getEventDate();
				if (eventDate.getMonth() == date.getMonth() && eventDate.getDate() == date.getDate()) {
					EventShow eventShow = new EventShow(primaryStage, event);
					eventShow.show();
				}
			}
		} 
		else if (savedArgs[0].equals("menu") || savedArgs[0].equals("m")) {
			EventMenu eventMenu = new EventMenu(primaryStage);
			eventMenu.show();
		}
	}
	
}
