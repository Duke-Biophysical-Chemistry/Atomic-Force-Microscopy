import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class AFMMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage stage;
	public static Parent root;
	public static AFMTrackPane trackPane = null;
	@Override
	public void start(Stage primaryStage) throws IOException {
		//load resources and set variables necessary to run the FXML loader
		AFMMain.stage = primaryStage;
		Font.loadFont(AFMMain.class.getResourceAsStream("expressway.ttf"), 12);	
		
		//load FXML graph and scene
		root = FXMLLoader.load(getClass().getResource("AFMLayoutLite.fxml"));
		Scene scene = new Scene (root);
		
		stage.setTitle("Duke Biophysical Chemistry AFM Viewer");
		stage.setScene(scene);
		stage.show();
		addNewTrack();

//		debug();
	}
	/*
	 * Getter / setter methods
	 */
	
	public AFMTrackPane getTrack(){
		return trackPane;
	}
	
	public void addNewTrack(){
		Node theTrack = root.lookup("#RunContainer");
		if (theTrack instanceof javafx.scene.layout.Pane){
			appendTrack((Pane) theTrack);
		}
		else{
			System.err.println("#Track is of the wrong type. Should be <AnchorPane>, is <"+theTrack.getClass()+">");
		}
	}
	
	private void appendTrack(Pane parent){
		trackPane = new AFMTrackPane(parent);
		trackPane.addTip(TipType.TRIANGLE_TIP);
		parent.getChildren().add(trackPane);
	}
	
	private void debug(){
		Node chart = root.lookup("#Chart");
		chart.setStyle("-fx-border-color:blue;");
		Node runcontainer = root.lookup("#RunContainer");
		runcontainer.setStyle("-fx-border-color:red;");
	}
}
