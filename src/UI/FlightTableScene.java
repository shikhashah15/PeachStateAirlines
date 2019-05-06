package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import BL.Flights;
import UI.DBConnector;
import UI.LoginScreen;

public class FlightTableScene {
	
	private static Stage window;
	private static Scene scene;
	private static Label lbl;
	private static Button search, home;
	private static String flightid;
	private static ComboBox<String> origin, destination, time;
	private static DatePicker date;
	private static VBox layout; 
	
	public static void initialize() {
		
		
		window = new Stage(); 
        lbl = new Label("Search for flights:");
        search = new Button("Search");
        home = new Button("Home"); 
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

        //button.setOnAction(e -> printMovie());

        //ComboBoxes also generate actions if you need to get value instantly
        origin.setOnAction( e -> System.out.println("User selected " + origin.getValue()) );
        destination.setOnAction( e -> System.out.println("User selected " + origin.getValue()) );
        time.setOnAction( e -> System.out.println("User selected " + origin.getValue()) );
        
        search.setOnAction(e -> {
        	
        	String flightOrigin = origin.getValue(); 
        	String flightDestination = destination.getValue(); 
        	String flightTime = time.getValue(); 
        	String flightDate = date.getValue().toString(); 
        	
        	Flights flight1 = new Flights(flightOrigin, flightDestination, flightTime, flightDate, LoginScreen.currentUsername);
        	// check database to see if flight exists 
        	if (DBConnector.flightExists(flight1, flightOrigin, flightDestination, flightDate, flightTime)) {
        		System.out.println("Flight Exists"); 
        		Flights searched = DBConnector.getFlight(flightOrigin,flightDestination,flightDate, flightTime); 
        		System.out.println(searched.getOrigin() + " " + searched.getDestination()+ " " +searched.getDate()+ " " +searched.getTime()); 
        		
        	}
        	
        	else {
        		
        		AnotherAlertBox.display("Sorry!", "That flight doesn't exist. Please try again.");
        		window.close();
        	}
        	
        });
        
       home.setOnAction(e -> {
    	   
    	   if (LoginScreen.currentUsername.equals("admin")) {
    		   
    		   AdminMainMenu.initialize();
    		   window.close();
    		   
    	   }
    	   
    	   else {
    		   
        	   UserMainMenu.initialize();
        	   window.close();

    	   }
      	
        }); 


        layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(lbl, origin, destination, date, time, search, home);

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

	public static Button getButton() {
		return search;
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

	public static Button getMyReservations() {
		return home;
	}	
	
}

