package com.lukashman.gui;

import java.time.LocalDate;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	
	Label informationLabel = new Label();
	
	public EventMenu(Stage parent) {
		initOwner(parent);
		initModality(Modality.APPLICATION_MODAL);
		
		BorderPane bPane = new BorderPane();
		
		Label nameLabel = new Label("Данные человека");
		Label typeLabel = new Label("Тип события");
		Label dateLabel = new Label("Дата");
		
		informationLabel.setVisible(false);
		informationLabel.setTextFill(Color.RED);
		
		tableView = new TableView();
		
		nameText = new TextField();
		typeChoise = new ChoiceBox<>(FXCollections.observableArrayList(EventType.BIRTH_DAY,EventType.MEMORY_DAY));
		datePicker = new DatePicker();
		
		Tooltip nameTooltip = new Tooltip("Впишите имя человека");
		Tooltip typeTooltip = new Tooltip("Выберите событие");
		Tooltip datepicketTooltip = new Tooltip("Выберите дату");
		
		nameTooltip.setFont(Font.font(15));
		typeTooltip.setFont(Font.font(15));
		datepicketTooltip.setFont(Font.font(15));
		
		nameText.setTooltip(nameTooltip);
		typeChoise.setTooltip(typeTooltip);
		datePicker.setTooltip(datepicketTooltip);
		
		addEvent = new Button("Add Event");
		updateEvent = new Button("Update Event");
		deleteEvent = new Button("Delete Event");
		
		HBox buttonBar = new HBox(addEvent,updateEvent,deleteEvent);
		buttonBar.setSpacing(10);
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		
		column1.setPercentWidth(20);
		column2.setPercentWidth(80);
		
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
		allFields.getColumnConstraints().addAll(column1,column2);
		
		VBox menuBar = new VBox(informationLabel,allFields,buttonBar);
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
				eventDAO.addEvent(new Event(nameText.getText(), date, typeChoise.getValue()));
				informationLabel.setVisible(false);
				refreshTable();
			} else {
				informationLabel.setText("Заполните все поля");
				informationLabel.setVisible(true);
			}
		});
		
		updateEvent.setOnAction((e) -> {
			Event event = tableView.getSelectionModel().getSelectedItem();
			if (event != null && !nameText.getText().isEmpty() && !datePicker.getValue().equals(null) && !typeChoise.getValue().isEmpty() ) {
				String name = nameText.getText() == null ? "" : nameText.getText();
				Date rawDate = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date convertedDate = rawDate == null ? null : rawDate;
				String eventType = typeChoise.getValue().isEmpty() ? "" : typeChoise.getValue();
				eventDAO.updateEvent(event, name, convertedDate, eventType);
				informationLabel.setVisible(false);
				refreshTable();
			} else {
				informationLabel.setText("Выберите запись для обновления и заполните поля");
				informationLabel.setVisible(true);
			}
		});
		
		deleteEvent.setOnAction((e) -> {
			Event event = tableView.getSelectionModel().getSelectedItem();
			if (event != null) {
				eventDAO.deleteEvent(event.getId());
				informationLabel.setVisible(false);
				refreshTable();
			} else {
				informationLabel.setText("Выберите запись для удаления");
				informationLabel.setVisible(true);
			}
		});
	}
	
	private void createTable(TableView<Event> eventTable) {
		
		TableColumn eventId = new TableColumn("Id");
		TableColumn eventName = new TableColumn("ФИО");
		TableColumn eventDate = new TableColumn("Дата");
		TableColumn eventType = new TableColumn("Событие");
		
		eventId.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
		eventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
		eventDate.setCellValueFactory(new PropertyValueFactory<Event, Date>("eventDate"));
		eventType.setCellValueFactory(new PropertyValueFactory<Event, String>("eventType"));
		
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
