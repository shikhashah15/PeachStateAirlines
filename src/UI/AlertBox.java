package UI;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

	
	
    public static void display(String title, String message) {
    	
    	Stage window; 
    	VBox layout; 
    	Button mainMenu; 
    	Label label; 
    	Scene scene; 
    	
        window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        label = new Label();
        label.setText(message);
        mainMenu = new Button("Back to main menu");
        mainMenu.setOnAction(e -> {
        	
        	LoginScreen.initialize();
        	window.close();
        	
        });

        layout = new VBox(15);
        layout.getChildren().addAll(label, mainMenu);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

}
