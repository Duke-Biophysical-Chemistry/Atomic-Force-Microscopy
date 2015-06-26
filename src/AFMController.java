import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class AFMController implements Initializable {

	@FXML
	private Button ExitButton;
	@FXML
	private Button ResetButton;
	@FXML
	private Button ChangeShapeButton;
	@FXML
	private Button StartButton;

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
		assert ResetButton != null : "fx:id=\"ResetButton\" was not injected: check your FXML file 'simple.fxml'.";
		assert ChangeShapeButton != null : "fx:id=\"ChangeShapeButton\" was not injected: check your FXML file 'simple.fxml'.";
		assert StartButton != null : "fx:id=\"StartButton\" was not injected: check your FXML file 'simple.fxml'.";

		/*
		 * Initialize Stage Options
		 */
		AFMMain.stage.setResizable(false);

		
		/*
		 * Set logic for buttons
		 */
		//ExitButton
		ExitButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				AFMMain.exit();
				System.exit(0);
			}
		});
		//ResetButton
		

	}
	
	
	/*
	 * Methods to color buttons when hover
	 */
	@FXML
	void ButtonHover(ActionEvent event){
		
	}
}
