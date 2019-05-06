package UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserMainMenu {
	
	private static Stage window;
	private static Scene scene;
	private static Button searchFlights, accountReservations;
	
	public static void initialize() {
		
		try {
				window = new Stage(); 
		        //Button 1
		        Label label1 = new Label("Welcome USER!!");
		        Button searchFlights = new Button("Search Flights");
		        Button accountReservations = new Button("Account Reservations");


		        //Layout 1 - children laid out in vertical column
		        VBox layout1 = new VBox();
		        layout1.getChildren().addAll(label1, searchFlights, accountReservations);
		        scene = new Scene(layout1, 700, 500);

		        window.setScene(scene);
		        window.setTitle("Title Here");
		        window.show();
		        
		        searchFlights.setOnAction(e -> {
		        	
		        	FlightTableScene.initialize();
		        	window.close(); 
		      
		        });
		        
		        accountReservations.setOnAction(e -> {
		        	
		        	UserFlights.initialize(); 
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
		return searchFlights;
	}

	public static Button getAccountReservations() {
		return accountReservations;
	}


}
