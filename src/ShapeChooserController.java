
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	private String[] shapeNames = new String[]{"Triangle", "Circle", "Tall Rectangle", "Delta Function"};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initShapes();
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
			currentSelectionLabel.setText(shapeNames[type]);
			currentSelectionImage.setImage(shapetypeToImage(type));
			currentSelectionImage.setFitHeight(DimensionConstants.getHeight(type));
			currentSelectionImage.setFitWidth(DimensionConstants.getWidth(type));
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
		setInitialTipImage(shapetypeToImage(type), DimensionConstants.getWidth(type), DimensionConstants.getHeight(type));
		close();
	}


}
