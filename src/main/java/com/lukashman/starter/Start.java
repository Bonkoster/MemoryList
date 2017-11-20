package com.lukashman.starter;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lukashman.config.AppContext;
import com.lukashman.dao.EventDAO;
import com.lukashman.dao.EventDAOImpl;
import com.lukashman.gui.EventMenu;
import com.lukashman.model.Event;
import com.lukashman.model.EventType;

import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		EventMenu eventMenu = new EventMenu(primaryStage);
		eventMenu.show();
	}
	
}
