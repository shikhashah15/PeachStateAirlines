package UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AnotherAlertBox {
	
    public static void display(String title, String message) {
    	
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button home = new Button("Home");
        home.setOnAction(e -> {
        	
        	if (LoginScreen.currentUsername.equals("admin")) {
        		
        		AdminMainMenu.initialize();
            	window.close();
            	
        	}
        	else {
        		
        		UserMainMenu.initialize();
        		window.close();
        		
        	}
        	
        });

        VBox layout = new VBox(15);
        layout.getChildren().addAll(label, home);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        
    }

}
