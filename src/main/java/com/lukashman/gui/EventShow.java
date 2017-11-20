package com.lukashman.gui;

import com.lukashman.model.Event;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventShow extends Stage {
	
	private Event event;
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public EventShow(Stage parent, Event event) {
		initOwner(parent);
		initModality(Modality.APPLICATION_MODAL);
		this.event = event;
		
		Label message = new Label("Today is " + event.getEventType() + " of " + event.getName());
		StackPane sPane = new StackPane();
		sPane.getChildren().add(message);
		setScene(new Scene(sPane,200,100));
		setTitle(event.getEventType());
		getIcons().add(new Image(EventShow.class.getResourceAsStream("/note.png")));
		show();
	}
}
