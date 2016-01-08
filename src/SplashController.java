

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import Shapes.Triangle;
import Constants.DimensionConstants;
import Constants.ShapeType;


public class SplashController extends AFMController implements Initializable {
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setInitialTipImage(new Triangle().getImage(), DimensionConstants.getImageWidth(ShapeType.triangle),
				DimensionConstants.getImageHeight(ShapeType.triangle));
		AFMMain.initialTipSelection = initialTip;	
		Platform.runLater(()->{	
			startButton.requestFocus();
			startButton.setDefaultButton(true);
		});
	}
}
