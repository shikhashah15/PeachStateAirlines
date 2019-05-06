package UI;

import javafx.geometry.Insets;
import UI.DBConnector;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginScreen {
	
	public static String currentUsername = null;
	private static Button userLogin, adminLogin, createAccount, forgotPassword; 
	private static Label username, password; 
	private static TextField usernameInput; 
	private static PasswordField passwordInput; 
	private static GridPane layout; 
	private static Scene scene;
	private static HBox buttonMenu; 
	private static BorderPane borderPane; 
	private static Stage window; 
	
	public static void initialize() {
		
		username = new Label("Username: "); 
		password = new Label("Password: "); 
		usernameInput = new TextField(); 
		passwordInput = new PasswordField(); 
		userLogin = new Button("User Login");
		adminLogin = new Button("Admin Login"); 
		createAccount = new Button("Create Account"); 
		forgotPassword = new Button("Forgot Password?"); 
		layout = new GridPane(); 
		layout.setPadding(new Insets(30,30,10,30));
		layout.setVgap(30);
		layout.setHgap(30);
		layout.setAlignment(Pos.BASELINE_CENTER);
		
		GridPane.setConstraints(username, 0, 0);
		GridPane.setConstraints(usernameInput, 1, 0);
		GridPane.setConstraints(password, 0, 1);
		GridPane.setConstraints(passwordInput, 1, 1);

		layout.getChildren().addAll(username, usernameInput, password, 
				passwordInput);

		buttonMenu = new HBox(15); 
		buttonMenu.getChildren().addAll(userLogin, adminLogin, createAccount, forgotPassword);
		buttonMenu.setAlignment(Pos.BASELINE_CENTER);
		buttonMenu.setPadding(new Insets(10,30,50,30));

		borderPane = new BorderPane(); 
		borderPane.setCenter(layout); 
		borderPane.setBottom(buttonMenu);

		scene = new Scene(borderPane, 550, 250); 
		window = new Stage(); 
		window.setScene(scene); 
		window.show();
		
		adminLogin.setOnAction(e-> {
			
			currentUsername = usernameInput.getText();
			if (currentUsername.equals("admin") && passwordInput.getText().equals("admin123")) {
				
				AdminMainMenu.initialize();
				window.close(); 
				
			}
			
			else {
				
				AlertBox.display("Wait!", "Admin credentials don't match. Please try again.");
				window.close();
				
			}
		}); 
		
		createAccount.setOnAction(e -> {
			
			RegistrationScene.initialize();
			window.close();
			
		});
		
		userLogin.setOnAction(e -> {
			
			currentUsername = usernameInput.getText(); 
			if(DBConnector.isValidUser(currentUsername, passwordInput.getText())) {
				
				UserMainMenu.initialize();
				window.close();
				
			}
			
			else {
				
				AlertBox.display("Wait!", "Your username or password is incorrect, please try again.");
				window.close();
			}
		}); 
		
		forgotPassword.setOnAction(e -> {
			
			ForgotPasswordScene.initialize(); 
			window.close();
			
		});
		
	}
	
	public static Button getUserLogin() {
		return userLogin;
	}
	public static Button getAdminLogin() {
		return adminLogin;
	}
	public static Button getCreateAccount() {
		return createAccount;
	}
	public static Button getForgotPassword() {
		return forgotPassword;
	}
	public static Label getUsername() {
		return username;
	}
	public static Label getPassword() {
		return password;
	}
	public static TextField getUsernameInput() {
		return usernameInput;
	}
	public static PasswordField getPasswordInput() {
		return passwordInput;
	}
	public static GridPane getLayout() {
		return layout;
	}
	public static Scene getScene() {
		return scene;
	}

	public static HBox getButtonMenu() {
		return buttonMenu;
	}

	public static BorderPane getBorderPane() {
		return borderPane;
	}

	public static Stage getWindow() {
		return window;
	}	

}
