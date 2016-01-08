
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import Constants.DimensionConstants;
import Constants.ShapeType;
import Shapes.Circle;
import Shapes.DeltaFunction;
import Shapes.TallRectangle;
import Shapes.Triangle;


public class ShapeChooserController extends AFMController implements
Initializable {

	@FXML
	public ImageView currentSelectionImage, IVTriangle, IVCircle, IVTallRectangle, IVDeltaFunction;

	@FXML
	public Label currentSelectionLabel;

	@FXML
	public StackPane DeltaStackPane;
	
	@FXML
	public Button okButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initShapes();
		okButton.setDefaultButton(true);
		
	}

	public void initShapes(){
		currentSelectionImage.setImage(getCurrentSelection());
		IVTriangle.setImage(new Triangle().getImage());
		IVCircle.setImage(new Circle().getImage());
		IVTallRectangle.setImage(new TallRectangle().getImage());
		IVDeltaFunction.setImage(new DeltaFunction().getImage());
	}

	public WritableImage getCurrentSelection(){
		return shapetypeToImage(getShapeType());
	}


	public void close(){
		//reset default due to okButton consuming all ENTER presses
		super.startButton.setDefaultButton(true);
		super.closePopup();
	}

	/*
	 * Methods that handle changing selected image
	 */
	public void triangleOnAction(){
		setSelectedShape(ShapeType.triangle);
	}
	public void circleOnAction(){
		setSelectedShape(ShapeType.circle);
	}
	public void tallRectangleOnAction(){
		setSelectedShape(ShapeType.tallRectangle);
	}
	public void deltaFunctionOnAction(){
		setSelectedShape(ShapeType.delta);
	}

	public void setSelectedShape(int type){
		Platform.runLater(()->{
			currentSelectionLabel.setText(ShapeType.SHAPE_NAMES[type]);
			currentSelectionImage.setImage(shapetypeToImage(type));
			currentSelectionImage.setFitHeight(DimensionConstants.getImageHeight(type));
			currentSelectionImage.setFitWidth(DimensionConstants.getImageWidth(type));
		});
	}

	public WritableImage shapetypeToImage(int type){
		switch(type){
		case ShapeType.triangle: 
			return new Triangle().getImage();
		case ShapeType.circle:
			return new Circle().getImage();
		case ShapeType.tallRectangle:
			return new TallRectangle().getImage();
		case ShapeType.delta:
			return new DeltaFunction().getImage();
		}
		return null;
	}

	public void ok(){
		int type = ShapeType.triangle;
		switch(currentSelectionLabel.getText()){
		case "Triangle": 
			type = ShapeType.triangle;
			break;
		case "Circle":
			type = ShapeType.circle;
			break;
		case "Tall Rectangle":
			type = ShapeType.tallRectangle;
			break;
		case "Delta Function":
			type = ShapeType.delta;
			break;
		}
		//inherited from AFMMain 
		setShapeType(type);
		setInitialTipImage(shapetypeToImage(type), DimensionConstants.getImageWidth(type), DimensionConstants.getImageHeight(type));
		close();
	}


}
