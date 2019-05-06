package UI;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrationScene {
	
	private static Scene scene;
	private static GridPane grid;
	private static Stage window; 
	private static Label firstNameLBL, 
		  		 lastNameLBL, 
		  		 addressLBL, 
		  		 zipcodeLBL, 
		  		 stateLBL, 
		  		 usernameLBL, 
		  		 passwordLBL,  
		  		 ssnLBL, 
		  		 securityQuestionLBL, 
		  		 answerLBL;
	private static TextField firstNameTXT,
	  		  		 lastNameTXT,
	  		  		 addressTXT,
	  		  		 zipcodeTXT,
	  		  		 stateTXT,
	  		  		 usernameTXT,
	  		  		 securityQuestionTXT,
	  		  		 answerTXT = null;
	private static PasswordField passwordTXT, ssnTXT = null; 
	
	private static Button create, mainMenu; 
	
	public static void initialize() {
		
        firstNameLBL = new Label("First Name:");
        lastNameLBL = new Label("Last Name:");  
        addressLBL = new Label("Address:");
        zipcodeLBL = new Label("Zipcode:");       
        stateLBL = new Label("State:");        
        usernameLBL = new Label("Username:");       
        passwordLBL = new Label("Password:");        
        ssnLBL = new Label("SSN:");       
        securityQuestionLBL = new Label("Security Question:");
        answerLBL = new Label("Answer:");
        
        firstNameTXT = new TextField();
        lastNameTXT = new TextField();  
        addressTXT = new TextField();
        zipcodeTXT = new TextField();       
        stateTXT = new TextField();        
        usernameTXT = new TextField();       
        passwordTXT = new PasswordField();   
        ssnTXT = new PasswordField();    
        ssnTXT.setPromptText("xxx-xx-xxxx"); 
        securityQuestionTXT = new TextField();
        answerTXT = new TextField();
        
        create = new Button("Create User");
        
        create.setOnAction(e -> {
        		
        	if (DBConnector.validInputs(firstNameTXT, lastNameTXT, addressTXT, zipcodeTXT, usernameTXT, stateTXT, passwordTXT,
        			ssnTXT, securityQuestionTXT, answerTXT)) {
        		
        		try {
        			
        			String sql = "insert into `flightAppUser` (`firstName`, `lastName`, `address`, `zipcode`, `state`, `username`, `pass`, `ssn`, `question`, `answer`)" 
        					+ "values (?,?,?,?,?,?,?,?,?,?)"; 
        			
        			Connection myConn = DBConnector.getConnection(); 
        			PreparedStatement myStmt = myConn.prepareStatement(sql); 
        		
        			//set parameter values 
        			myStmt.setString(1, firstNameTXT.getText());
        			myStmt.setString(2, lastNameTXT.getText());
        			myStmt.setString(3, addressTXT.getText());
        			myStmt.setString(4, zipcodeTXT.getText());
        			myStmt.setString(5, stateTXT.getText());
        			myStmt.setString(6, usernameTXT.getText());
        			myStmt.setString(7, passwordTXT.getText());
        			myStmt.setString(8, ssnTXT.getText());
    				myStmt.setString(9, securityQuestionTXT.getText());
    				myStmt.setString(10, answerTXT.getText());

    				//execute query 
    				myStmt.executeUpdate(); 
    				System.out.println("Account added to database"); 
    				AlertBox.display("Account Created", "Your account has been created, please log in");
    				window.hide();
    				
        		} catch (Exception ex) {
        		
        			ex.printStackTrace();
        		
        		
        		}
        	
        	}
        	
        	else {
        		
        		AlertBox.display("Invalid Inputs", "You must have values for each field. \nAccount could not be created.");
        		window.close();
        		
        	}

        });
        
        mainMenu = new Button("Main Menu"); 
        mainMenu.setOnAction(e -> {
        	
        	LoginScreen.initialize();
        	window.close();
        	
        });
        window = new Stage(); 
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,30,30,30));
        grid.setVgap(15);
        grid.setHgap(12);
        
        GridPane.setConstraints(firstNameLBL, 0, 0);
        GridPane.setConstraints(lastNameLBL, 0 , 1);
        GridPane.setConstraints(addressLBL, 0, 2);
        GridPane.setConstraints(zipcodeLBL, 0, 3);
        GridPane.setConstraints(stateLBL, 0, 4);
        GridPane.setConstraints(usernameLBL, 0, 5);
        GridPane.setConstraints(passwordLBL, 0, 6);
        GridPane.setConstraints(ssnLBL, 0, 7);
        GridPane.setConstraints(securityQuestionLBL, 0, 8);
        GridPane.setConstraints(answerLBL, 0, 9);
        
        GridPane.setConstraints(firstNameTXT, 1, 0);
        GridPane.setConstraints(lastNameTXT, 1, 1);
        GridPane.setConstraints(addressTXT, 1, 2);
        GridPane.setConstraints(zipcodeTXT, 1, 3);
        GridPane.setConstraints(stateTXT, 1, 4);
        GridPane.setConstraints(usernameTXT, 1, 5);
        GridPane.setConstraints(passwordTXT, 1, 6);
        GridPane.setConstraints(ssnTXT, 1, 7);
        GridPane.setConstraints(securityQuestionTXT, 1, 8);
        GridPane.setConstraints(answerTXT, 1, 9);
        GridPane.setConstraints(mainMenu, 0, 10);
        GridPane.setConstraints(create, 1, 10);
        
        grid.getChildren().addAll(firstNameLBL, lastNameLBL, addressLBL, zipcodeLBL, stateLBL, usernameLBL, passwordLBL, 
        		ssnLBL, securityQuestionLBL, answerLBL, firstNameTXT, lastNameTXT, addressTXT, zipcodeTXT, stateTXT, usernameTXT, passwordTXT,
        		 ssnTXT, securityQuestionTXT, answerTXT, create, mainMenu);

		scene = new Scene(grid,400,500);  

		window.setTitle("Create Account");
		window.setScene(scene); 
		window.show();
        
        System.out.println("registration screen initialized");
        
	}

	public Scene getScene() {
		return scene;
	}

	public GridPane getGrid() {
		return grid;
	}

	public static Label getFirstNameLBL() {
		return firstNameLBL;
	}

	public static Label getLastNameLBL() {
		return lastNameLBL;
	}

	public static Label getAddressLBL() {
		return addressLBL;
	}

	public static Label getZipcodeLBL() {
		return zipcodeLBL;
	}

	public static Label getStateLBL() {
		return stateLBL;
	}

	public static Label getUsernameLBL() {
		return usernameLBL;
	}

	public static Label getPasswordLBL() {
		return passwordLBL;
	}

	public static Label getSsnLBL() {
		return ssnLBL;
	}

	public static Label getSecurityQuestionLBL() {
		return securityQuestionLBL;
	}

	public static Label getAnswerLBL() {
		return answerLBL;
	}

	public static TextField getFirstNameTXT() {
		return firstNameTXT;
	}

	public static TextField getLastNameTXT() {
		return lastNameTXT;
	}

	public static TextField getAddressTXT() {
		return addressTXT;
	}

	public static TextField getZipcodeTXT() {
		return zipcodeTXT;
	}

	public static TextField getStateTXT() {
		return stateTXT;
	}

	public static TextField getUsernameTXT() {
		return usernameTXT;
	}

	public static TextField getPasswordTXT() {
		return passwordTXT;
	}

	public static TextField getSsnTXT() {
		return ssnTXT;
	}

	public static TextField getSecurityQuestionTXT() {
		return securityQuestionTXT;
	}

	public static TextField getAnswerTXT() {
		return answerTXT;
	}

	public static Button getLoginButton() {
		return create;
	}

	public static Button getMainMenu() {
		return mainMenu;
	}
	
}
