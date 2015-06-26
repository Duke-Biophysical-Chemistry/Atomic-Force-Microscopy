import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class AFMMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		//load resources and set variables necessary to run the FXML loader
		AFMMain.stage = primaryStage;
		Font.loadFont(AFMMain.class.getResourceAsStream("expressway.ttf"), 12);	
		
		//load FXML graph and scene
		Parent root = FXMLLoader.load(getClass().getResource("AFMLayoutLite.fxml"));
		Scene scene = new Scene (root);
		
		stage.setTitle("Duke Biophysical Chemistry AFM Viewer");
		stage.setScene(scene);
		stage.show();
	}
	
	
	//Closes the stage
	public static void exit(){
		Platform.exit();
	}

}
