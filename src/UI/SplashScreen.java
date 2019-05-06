package UI;

import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.*;

public class SplashScreen {
	
	private static Stage window;
	private static Button clickHere; 
	private static BorderPane root; 
	private static Scene scene; 
	private static Text tx; 
	
	
	public static void initialize() {
		
		window = new Stage(); 
		window.setTitle("PeachState Airlines");
		clickHere = new Button("Click Here"); 
		clickHere.setOnAction(e -> {
			
			LoginScreen.initialize();
			window.close();
			
		});
	    root = new BorderPane();
	    root.setPadding(new Insets(10,10,10,10));
	    Scene scene = new Scene(root, 600, 550);
	    tx = new Text("Welcome to PeachState Airlines...");
	    tx.setFont(new Font("Times New Roman", 20));
	    root.setTop(tx);
	    root.setBottom(clickHere);
	    root.getChildren().addAll();
	    window.setScene(scene);
	    window.show();
	    
	}
	
	public static Stage getWindow() {
		
		return window; 
		
	}
	
	public static Button getClickHere() {
		
		return clickHere; 
		
	}

	public static BorderPane getRoot() {
		
		return root;
		
	}

	public static Scene getScene() {
		
		return scene;
		
	}

	public static Text getTx() {
		
		return tx;
		
	}



}
