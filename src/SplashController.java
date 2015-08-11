

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import Shapes.Triangle;
import Constants.DimensionConstants;


public class SplashController extends AFMController implements Initializable {
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setInitialTipImage(new Triangle().getImage(), DimensionConstants.AllOtherWidths);
		AFMMain.initialTipSelection = initialTip;
	}
}
