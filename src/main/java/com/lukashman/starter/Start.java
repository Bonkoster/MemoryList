package com.lukashman.starter;

import java.util.Date;

import com.lukashman.gui.EventMenu;
import com.lukashman.gui.EventShow;
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
