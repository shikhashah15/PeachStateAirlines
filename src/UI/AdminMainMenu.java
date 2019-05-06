package UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMainMenu {
	
	private static Stage window;
	private static Scene scene;
	private static Button changeFlights, accountReservations, searchFlights, mainMenu; 
	
	public static void initialize() {
		
		try {
				window = new Stage(); 
		        //Button 1
		        Label label1 = new Label("Welcome Admin!");
		        searchFlights = new Button("Search for Flights"); 
		        changeFlights = new Button("Modify Flights");
		        accountReservations = new Button("My Reservations");
		        mainMenu = new Button("Main Menu"); 

		        //Layout 1 - children laid out in vertical column
		        VBox layout1 = new VBox(15);
		        layout1.getChildren().addAll(label1, changeFlights, accountReservations, searchFlights, mainMenu);
		        scene = new Scene(layout1, 700, 500);
		        layout1.setAlignment(Pos.BASELINE_CENTER); 
		        window.setScene(scene);
		        window.setTitle("Admin Home");
		        window.show();
		        
		        accountReservations.setOnAction(e -> {
		        	
		        	UserFlights.initialize(); 
		        	window.close();
		        	
		        });
		        
		        changeFlights.setOnAction(e -> {
		        	
		        	AdminFlights.initialize();
		        	window.close();
		        	
		        });
		        
		        searchFlights.setOnAction(e -> {
		        	
		        	FlightTableScene.initialize();
		        	window.close(); 
		        	
		        });
		        
		        mainMenu.setOnAction(e -> {
		        	
		        	LoginScreen.initialize(); 
		        	window.close(); 
		        	
		        });
		        
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		
	}

	public static Stage getWindow() {
		return window;
	}

	public static Scene getScene() {
		return scene;
	}

	public static Button getSearchFlights() {
		return changeFlights;
	}

	public static Button getAccountReservations() {
		return accountReservations;
	}


}

