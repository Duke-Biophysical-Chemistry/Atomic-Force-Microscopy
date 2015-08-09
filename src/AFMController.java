import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class AFMController extends AFMMain implements Initializable {

	public static final double CONTENT_HEIGHT = 371;
	public static final double CONTENT_WIDTH = 636;
	
	public static final double CANVAS_HEIGHT = 178;
	public static final double CANVAS_WIDTH = 629;
	
	@FXML
	public Menu fileMenu, helpMenu;
	
	@FXML
	public MenuItem restartItem, quitItem, courseSite, About;
	
	@FXML
	public Group tipGroup;
	
	@FXML
	public ScatterChart Chart;
	
	@FXML
	public javafx.scene.canvas.Canvas Canvas;
	
	@FXML
	public Button exitButton, startButton, CancelRun, SaveRun;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void initButtons(){
		
	}
	
	public void goToSite(String site){
		try {
			Desktop.getDesktop().browse(new URI(site));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goToSourceCode(){
		final String url = "https://www.github.com/Duke-Biophysical-Chemistry/Atomic-Force-Microscopy";
		goToSite(url);
	}
	
	public void goToCourseSite(){
		final String url = "https://www.sakai.duke.edu";
		goToSite(url);
	}
	
	public void exit(){
		super.exit();
	}
	
	public void restart(){
		super.restart();
	}

	public void begin(){
		super.begin();
	}
	
	public void changeShapes(){
		super.changeShapes();
	}
	
	public void cancelRun(){
		super.showSplash();
	}
	
	public void saveRun(){
		
	}
}
