package UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserMainMenu {
	
	private static Stage window;
	private static Scene scene;
	private static Button bookFlights, accountReservations, mainMenu;
	private static Label label; 
	private static VBox layout; 
	
	public static void initialize() {
		
		try {
				window = new Stage(); 
				mainMenu = new Button("Main Menu"); 
		       
		        label = new Label();
		        label.setText("Welcome " + LoginScreen.currentUsername + "!");
		        bookFlights = new Button("Book Flights");
		        accountReservations = new Button("My Reservations");
		      

		        layout = new VBox(15);
		        
		        layout.setAlignment(Pos.BASELINE_CENTER);
		        
		        layout.getChildren().addAll(label, bookFlights, accountReservations, mainMenu);
		        scene = new Scene(layout, 700, 500);
		        window.setScene(scene);
		        window.setTitle("Home");
		        window.show();
		        
		        bookFlights.setOnAction(e -> {
		        	
		        	FlightTableScene.initialize();
		        	window.close(); 
		      
		        });
		        
		        accountReservations.setOnAction(e -> {
		        	
		        	UserFlights.initialize(); 
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

	public static Button getBookFlights() {
		return bookFlights;
	}

	public static Button getAccountReservations() {
		return accountReservations;
	}

	public static Button getMainMenu() {
		return mainMenu;
	}

	public static Label getLabel() {
		return label;
	}

	public static VBox getLayout() {
		return layout;
	}


}
