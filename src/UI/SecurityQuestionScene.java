package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecurityQuestionScene {
	
	private static Label question; 
	private static VBox layout; 
	private static Stage window; 
	private static TextField answerInput; 
	private static Button submit; 
	private static Scene scene;
	public static Label getQuestion() {
		return question;
	}
	public static VBox getLayout() {
		return layout;
	}
	public static Stage getWindow() {
		return window;
	}
	public static TextField getAnswerInput() {
		return answerInput;
	}
	public static Button getSubmit() {
		return submit;
	}
	public static Scene getScene() {
		return scene;
	} 

	public static void initialize(String username) {
				
		window = new Stage(); 
		question = new Label(); 
		question.setText(DBConnector.displaySecurityQuestion(username));
		answerInput = new TextField(); 
		submit = new Button("Submit"); 
		submit.setOnAction(e -> {
			
			if (!(answerInput.getText().isEmpty())) {
			 try{	
				if (DBConnector.verifyAnswer(username, answerInput.getText())) {
					
					DisplayPasswordScene.initialize(username);
					window.close();
					
				}
				
				else {
						
						AlertBox.display("Wait!", "Your answer is wrong. \nPlease try again.");
						window.close();
					}
				
				} catch (Exception ex) {
					
					ex.printStackTrace();
					
				}
				
			}
			
			else {
				
				AlertBox.display("Wait!", "You didn't enter anything. \nPlease try again.");
				window.close();
				
			}
			
			
			
		});
		layout = new VBox(30); 
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.setPadding(new Insets(10,30,15,15));
		layout.getChildren().addAll(question, answerInput, submit); 
		scene = new Scene(layout, 400, 200); 
		window.setScene(scene);
		window.show();
		
	}

}
