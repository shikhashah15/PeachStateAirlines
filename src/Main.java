
	
import UI.SplashScreen;
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		SplashScreen.initialize();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
