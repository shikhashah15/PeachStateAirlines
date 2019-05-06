package UI;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import BL.Flights;
import UI.DBConnector;

public class AdminFlights {
	
	private static Stage window;
	private static Button mainMenu;
	private static Button delete, update, add, home;
	private static ObservableList<Flights> oblist;
	private static VBox vbox;
	private static Scene scene; 
	private static TableView<Flights> tb; 
	private static TextField origin, destination, date, time; 
	
	@SuppressWarnings("unchecked")
	public static void initialize() {
		
		mainMenu = new Button("Main Menu");
		delete = new Button("Delete a Flight");
		add = new Button("Add a Flight"); 
		home = new Button("Home"); 
		update = new Button("Update a flight"); 
		window = new Stage(); 
		origin = new TextField(); 
		origin.setPromptText("enter origin");
		destination = new TextField(); 
		destination.setPromptText("Enter destination"); 
		date = new TextField(); 
		date.setPromptText("Enter date");
		time = new TextField(); 
		time.setPromptText("Enter time");
		
		try {
			
			tb = new TableView<Flights>();
			
			TableColumn<Flights, String> col1 = new TableColumn<>("Origin");
			col1.setCellValueFactory(new PropertyValueFactory<>("origin"));
			
			TableColumn<Flights, String> col2 = new TableColumn<>("Destination");
			col2.setCellValueFactory(new PropertyValueFactory<>("destination"));
			
			TableColumn<Flights, String> col3 = new TableColumn<>("Time");
			col3.setCellValueFactory(new PropertyValueFactory<>("time"));
			
			TableColumn<Flights, String> col4 = new TableColumn<>("Date");
			col4.setCellValueFactory(new PropertyValueFactory<>("date"));
			
			tb.getColumns().addAll(col1, col2, col3, col4);
			
      
        	oblist = DBConnector.getAllFlights();
        
			tb.setItems(oblist);
		    vbox = new VBox(tb);
		    vbox.getChildren().addAll(delete, add, home, origin, destination, time, date, update); 
			scene = new Scene(vbox, 700, 550);
			window.setScene(scene);
			window.show();
			
			home.setOnAction(e -> {
				
				AdminMainMenu.initialize();
				window.close();
				
			});
			
			delete.setOnAction(e -> {
				
				try {
					
					Flights selectedFlight = (Flights) tb.getSelectionModel().getSelectedItem();
					tb.getItems().remove(selectedFlight); 
					
					DBConnector.adminDeleteFlight(selectedFlight);
				}
				
				catch (Exception ex) {
					
					AnotherAlertBox.display("Wait!","You haven't selected anything. \nPlease try again."); 
					window.close();

				}
				 
				
			});
			
			update.setOnAction(e -> {
				
				try {
					
					Flights selectedFlight = (Flights) tb.getSelectionModel().getSelectedItem();
					tb.getItems().remove(selectedFlight); 
					Flights newFlight = DBConnector.adminUpdateFlight(selectedFlight, origin, destination, time, date);				
					tb.getItems().add(newFlight); 
					origin.clear(); 
					destination.clear();
					time.clear();
					date.clear();
					
				} catch (Exception ex) {
					
					AnotherAlertBox.display("Wait!","You haven't selected anything. \nPlease try again."); 
					window.close();
					
				}				
				
			});
			
			add.setOnAction(e -> {
				
				AdminAddFlight.initialize();
				window.hide();
				
			});
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
	}

	public static Stage getWindow() {
		return window;
	}

	public static Button getMainMenu() {
		return mainMenu;
	}

	public static Button getDelete() {
		return delete;
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

	public static Button getUpdate() {
		return update;
	}

	public static Button getAdd() {
		return add;
	}

	public static Button getHome() {
		return home;
	}

	public static TableView<Flights> getTb() {
		return tb;
	}

	public static TextField getOrigin() {
		return origin;
	}

	public static TextField getDestination() {
		return destination;
	}

	public static TextField getDate() {
		return date;
	}

	public static TextField getTime() {
		return time;
	}
	
	

}