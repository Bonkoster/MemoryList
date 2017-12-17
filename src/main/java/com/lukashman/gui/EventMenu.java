package com.lukashman.gui;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lukashman.config.AppContext;
import com.lukashman.dao.EventDAO;
import com.lukashman.dao.EventDAOImpl;
import com.lukashman.model.Event;
import com.lukashman.model.EventType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventMenu extends Stage {
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
	
	EventDAO eventDAO = ctx.getBean(EventDAOImpl.class);
	
	Button addEvent;
	Button updateEvent;
	Button deleteEvent;
	
	TextField nameText;
	ChoiceBox<String> typeChoise;
	DatePicker datePicker;
	
	TableView<Event> tableView;
	
	public EventMenu(Stage parent) {
		initOwner(parent);
		initModality(Modality.APPLICATION_MODAL);
		
		BorderPane bPane = new BorderPane();
		
		Label nameLabel = new Label("Данные человека");
		Label typeLabel = new Label("Тип события");
		Label dateLabel = new Label("Дата");
		
		tableView = new TableView();
		
		nameText = new TextField();
		typeChoise = new ChoiceBox<>(FXCollections.observableArrayList(EventType.BIRTH_DAY,EventType.MEMORY_DAY));
		datePicker = new DatePicker();
		
		addEvent = new Button("Add Event");
		updateEvent = new Button("Update Event");
		deleteEvent = new Button("Delete Event");
		
		HBox buttonBar = new HBox(addEvent,updateEvent,deleteEvent);
		buttonBar.setSpacing(10);
		
		GridPane allFields = new GridPane();
		
		allFields.add(nameLabel, 0, 1);
		allFields.add(nameText, 1, 1);
		allFields.add(typeLabel, 0, 2);
		allFields.add(typeChoise, 1, 2);
		allFields.add(dateLabel, 0, 3);
		allFields.add(datePicker, 1, 3);
		allFields.setPadding(new Insets(5.0));
		allFields.setHgap(5);
		allFields.setVgap(5);
		
		VBox menuBar = new VBox(allFields,buttonBar);
		createTable(tableView);
		getTableRows(tableView);
		setButtonEvents();
		
		bPane.setBottom(menuBar);
		bPane.setCenter(tableView);
		getIcons().add(new Image(EventMenu.class.getResourceAsStream("/note.png")));
		setScene(new Scene(bPane,600,600));
		setTitle("Сборник памятных дат");
	}
	
	private void setButtonEvents() {
		addEvent.setOnAction((e) -> {
			if (!nameText.getText().isEmpty() && !datePicker.getValue().equals(null) && !typeChoise.getValue().isEmpty()) {
				Date date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				eventDAO.addEvent(new Event(nameText.getText(), date , typeChoise.getValue()));
				refreshTable();
			} else {
				
			}
		});
		deleteEvent.setOnAction((e) -> {
			Event event = tableView.getSelectionModel().getSelectedItem();
			if (!event.equals(null)) {
				eventDAO.deleteEvent(event.getId());
				refreshTable();
			} else {
				
			}
		});
	}
	
	private void createTable(TableView<Event> eventTable) {
		TableColumn eventId = new TableColumn("Id");
		TableColumn eventName = new TableColumn("Name");
		TableColumn eventDate = new TableColumn("Date");
		TableColumn eventType = new TableColumn("Event type");
		
		eventId.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
		eventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
		eventDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("event_date"));
		eventType.setCellValueFactory(new PropertyValueFactory<Event, String>("event_type"));
		
		eventTable.getColumns().addAll(eventId,eventName,eventDate,eventType);
	}
	
	private void getTableRows(TableView<Event> tableView) {
		List<Event> records = eventDAO.getAllEvents();
		ObservableList<Event> tableRecords = FXCollections.observableArrayList(records);
		tableView.setItems(tableRecords);
	}
	
	private void refreshTable() {
		getTableRows(tableView);
	}

}
