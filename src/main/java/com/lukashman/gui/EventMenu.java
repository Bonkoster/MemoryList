package com.lukashman.gui;

import java.util.Arrays;

import com.lukashman.model.EventType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventMenu extends Stage {
	public EventMenu(Stage parent) {
		initOwner(parent);
		initModality(Modality.APPLICATION_MODAL);
		
		BorderPane bPane = new BorderPane();
		
		Label nameLabel = new Label("Данные человека");
		Label typeLabel = new Label("Тип события");
		
		TextField nameText = new TextField();
		ChoiceBox<String> typeChoise = new ChoiceBox<>(FXCollections.observableArrayList(EventType.BIRTH_DAY,EventType.MEMORY_DAY));
		
		DatePicker datePicker = new DatePicker();
		
		Button addEvent = new Button("Add Event");
		
		HBox nameField = new HBox(nameLabel,nameText);
		HBox typeField = new HBox(typeLabel,typeChoise);
		VBox allFields = new VBox(nameField,typeField,datePicker,addEvent);
		
		bPane.setBottom(allFields);
		setScene(new Scene(bPane,600,600));
		
	}

}
