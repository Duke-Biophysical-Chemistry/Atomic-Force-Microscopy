import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Injects code into the FXML graph for controlling the controller objects and associated handlers
 * @author jkarnuta
 *
 */
public class AFMController extends AFMMain implements Initializable {

	@FXML
	private Button ExitButton;
	@FXML
	private Button RestartButton;
	@FXML
	private Button ChangeShapeButton;
	@FXML
	private Button StartButton;
	@FXML
	private Label AFMLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*
		 * Initialize Buttons:
		 * 
		 * ExitButton
		 * ResetButton
		 * ChangeShapeButtion
		 * StartButton
		 */
		assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'simple.fxml'.";
		assert RestartButton != null : "fx:id=\"ResetButton\" was not injected: check your FXML file 'simple.fxml'.";
		assert ChangeShapeButton != null : "fx:id=\"ChangeShapeButton\" was not injected: check your FXML file 'simple.fxml'.";
		assert StartButton != null : "fx:id=\"StartButton\" was not injected: check your FXML file 'simple.fxml'.";

		/*
		 * Initialize Stage Options
		 */
		AFMMain.stage.setResizable(false);

	}
	/*
	 * Set logic for buttons
	 */
	public void ExitButtonOnAction(){
		exit();
	}

	public void RestartButtonOnAction(){
		restart();
	}

	public void ChangeShapeButtonOnAction(){
		modalChangeShapeDialog();
	}
	public void StartButtonOnAction(){
		run();
	}

	/*
	 * Set logic for label to direct to sakai / course website
	 */
	public void CourseLabelOnMouseClick(){
		directToCourseSite();
		System.out.println("clicked");
	}

	/*
	 * Referenced Button methods 
	 */

	//Exits the javaFX thread
	private void exit(){
		Platform.exit();
		System.exit(0);
	}
	//Closes the stage
	private void restart(){
		stage.close();
		try {
			start(new Stage());
			System.out.println("New Stage Initiated");
		} catch (IOException e) {
			System.err.println("Error starting new stage");
			e.printStackTrace();
		}
	}
	//changes shape of tip via modal dialog
	private void modalChangeShapeDialog(){
		//TODO: Implement modal dialog w/ flowpane
		System.out.println("Not Implemented");
	}

	//begins the AFM sequence
	private void run(){
		//TODO: Implement running algorithm
		System.out.println("Should run when implemented");
	}

	/*
	 * Referenced label methods
	 */

	private void directToCourseSite(){
		try{
			URI courseSite = new URI("www.sakai.duke.edu");
			Desktop.getDesktop().browse(courseSite);
		} catch (IOException | URISyntaxException e) {
			System.err.println("Cannot access AWT thread / default browser");
			e.printStackTrace();
		}
	}

}
