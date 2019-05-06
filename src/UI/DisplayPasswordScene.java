package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DisplayPasswordScene {
	
	private static Label yourPassword; 
	private static Label password; 
	private static Button logIn;
	private static VBox layout; 
	private static Stage window; 
	private static Scene scene;
	
	public static Label getYourPassword() {
		return yourPassword;
	}
	public static Label getPassword() {
		return password;
	}
	public static Button getLogIn() {
		return logIn;
	}
	public static VBox getLayout() {
		return layout;
	}
	public static Stage getWindow() {
		return window;
	}
	public static Scene getScene() {
		return scene;
	} 
	
	public static void initialize(String username) {
		
		window = new Stage(); 
		yourPassword = new Label("Your password is: "); 
		password = new Label(); 
		logIn = new Button("Log In"); 
		logIn.setOnAction(e -> {
			
			LoginScreen.initialize();
			window.hide();
			
		});
		password.setText(DBConnector.displayPassword(username).toString());
		
		layout = new VBox(30); 
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.setPadding(new Insets(10,30,15,15));
		layout.getChildren().addAll(yourPassword, password, logIn); 
		scene = new Scene(layout, 400, 200); 
		window.setScene(scene);
		window.show();
		
	}
	

}
