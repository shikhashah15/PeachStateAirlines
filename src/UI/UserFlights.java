package UI;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import BL.Flights;
import UI.DBConnector;
import UI.LoginScreen;


public class UserFlights {
	
	private static Stage window;
	private static Button home;
	private static Button remove;
	public static ObservableList<Flights> oblist;
	private static VBox vbox;
	private static Scene scene; 
	public static TableView<Flights> table;
	
	@SuppressWarnings("unchecked")
	public static void initialize() {
		
		home = new Button("Main Menu");
		remove = new Button("Remove Flight");
		window = new Stage(); 
		
		try {
			
			@SuppressWarnings("rawtypes")
			TableView<Flights> tb = new TableView();
			
			TableColumn<Flights, String> col1 = new TableColumn<>("Origin");
			col1.setCellValueFactory(new PropertyValueFactory<>("origin"));
			
			TableColumn<Flights, String> col2 = new TableColumn<>("Destination");
			col2.setCellValueFactory(new PropertyValueFactory<>("destination"));
			
			TableColumn<Flights, String> col3 = new TableColumn<>("Time");
			col3.setCellValueFactory(new PropertyValueFactory<>("time"));
			
			TableColumn<Flights, String> col4 = new TableColumn<>("Date");
			col4.setCellValueFactory(new PropertyValueFactory<>("date"));
			
			tb.getColumns().addAll(col1, col2, col3, col4);
			     
        	oblist = DBConnector.getFlights(LoginScreen.currentUsername);
        
			tb.setItems(oblist);
		    vbox = new VBox(tb);
		    vbox.getChildren().addAll(remove, home); 
			scene = new Scene(vbox, 700, 500);
			window.setScene(scene);
			window.show();
			remove.setOnAction(e -> {
				
				Flights selectedFlight = (Flights) tb.getSelectionModel().getSelectedItem();
				tb.getItems().remove(selectedFlight); 
				
				DBConnector.deleteFlight(LoginScreen.currentUsername, selectedFlight); 
				
			});
			
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
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
	}

	public static Stage getWindow() {
		return window;
	}

	public static Button getMainMenu() {
		return home;
	}

	public static Button getDelete() {
		return remove;
	}

	public static ObservableList<Flights> getOblist() {
		return oblist;
	}

	public static VBox getVbox() {
		return vbox;
	}

	public static Scene getScene() {
		return scene;
	}
	
	

}
