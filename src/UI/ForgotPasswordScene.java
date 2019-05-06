package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ForgotPasswordScene {
	
	private static Label username; 
	private static VBox layout; 
	private static Stage window; 
	private static TextField usernameInput; 
	private static Button submit; 
	private static Scene scene; 

	public static void initialize() {
		
		window = new Stage(); 
		username = new Label("Please enter your username: "); 
		usernameInput = new TextField(); 
		submit = new Button("Submit"); 
		
		submit.setOnAction(e -> {
			
			
			if (!(usernameInput.getText().isEmpty())) {
				
				try {
					
					if (DBConnector.userExists(usernameInput.getText())) {
						
						System.out.println("User Exists");
						SecurityQuestionScene.initialize(usernameInput.getText());
						window.close();					
						
					}
					
					else {
						
						AlertBox.display("Wait!", "Your username does not exist. \nPlease try again.");

					}
					
				}
				
				catch( Exception ex) {
					
					ex.printStackTrace(); 

				}
					
				}
			
			else {
				
				AlertBox.display("Wait!", "You didn't enter anything. \nPlease try again.");
				window.hide();
			}
			
					
		});
		
		layout = new VBox(30); 
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.setPadding(new Insets(10,30,15,15));
		layout.getChildren().addAll(username, usernameInput, submit); 
		scene = new Scene(layout, 400, 200); 
		window.setScene(scene);
		window.show();
		
	}

	public static Label getUsername() {
		return username;
	}

	public static VBox getLayout() {
		return layout;
	}

	public static Stage getWindow() {
		return window;
	}

	public static TextField getUsernameInput() {
		return usernameInput;
	}

	public static Button getSubmit() {
		return submit;
	}

	public static Scene getScene() {
		return scene;
	}

}
