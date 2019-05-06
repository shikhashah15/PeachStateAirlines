package UI;

import BL.Flights;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminAddFlight {
	
	private static Stage window;
	private static Scene scene;
	private static Label lbl;
	private static Button add, home; 
	private static String flightid;
	private static ComboBox<String> origin, destination, time;
	private static DatePicker date;
	private static VBox layout; 
	
	public static void initialize() {
		
		
		window = new Stage(); 
        lbl = new Label("Add a flight:");
        add = new Button("Add"); 
        
        origin = new ComboBox<>();
        destination = new ComboBox<>();
        date = new DatePicker();
        time = new ComboBox<>();

        origin.getItems().addAll(
                "Atlanta");
        
        destination.getItems().addAll(
        		
                "Los Angeles",
                "New York",
                "Houston",
                "Seattle",
                "San Franscisco");
        
        time.getItems().addAll(
                "9:00am", "12:00pm", "1:00pm", "3:00pm",
                "5:00pm", "7:00pm", "9:00pm"
                );
       
        origin.setPromptText("From: ");
        destination.setPromptText("To: ");
        date.setPromptText("Select the date: ");
        time.setPromptText("Time: ");
        
        date.setMaxWidth(300);

        
        add.setOnAction(e -> {
        	
        	Flights flight1 = new Flights(origin.getValue(), destination.getValue(), time.getValue(), date.getValue().toString(), LoginScreen.currentUsername);
        	DBConnector.insertFlightDB(flight1);
        	AdminFlights.initialize();
        	
        });
     


        layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(lbl, origin, destination, date, time, add);

        scene = new Scene(layout, 300, 300);
        window.setScene(scene);
        window.show();
	  
	}

	public static Stage getWindow() {
		return window;
	}

	public static Scene getScene() {
		return scene;
	}

	public static Label getLbl() {
		return lbl;
	}

	public static String getFlightid() {
		return flightid;
	}

	public static ComboBox<String> getOrigin() {
		return origin;
	}

	public static ComboBox<String> getDestination() {
		return destination;
	}

	public static ComboBox<String> getTime() {
		return time;
	}

	public static DatePicker getDate() {
		return date;
	}

	public static VBox getLayout() {
		return layout;
	}

	public static Button getAdd() {
		return add;
	}

	public static Button getHome() {
		return home;
	}
	
}
