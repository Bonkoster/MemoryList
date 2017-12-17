package com.lukashman.starter;

import com.lukashman.gui.EventMenu;


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
