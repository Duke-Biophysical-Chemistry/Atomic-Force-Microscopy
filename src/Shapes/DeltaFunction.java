package Shapes;

import Constants.ShapeType;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class DeltaFunction extends javafx.scene.shape.Rectangle{

	public static final Double scaleFactor = 4.0d;
	public static final Double WIDTH = 5.0d;
	public static final double HEIGHT = 60.0d;
	
	public DeltaFunction(){
		
		super(WIDTH, HEIGHT);
		
		setFill(ShapeType.SHAPE_COLOR);
		setStroke(ShapeType.SHAPE_COLOR);
	}

	public WritableImage getImage(){
		setScaleX(scaleFactor);
		setScaleY(scaleFactor);

		SnapshotParameters parameters = new SnapshotParameters();
		parameters.setFill(Color.TRANSPARENT);
		WritableImage image = this.snapshot(parameters, null);
	
		setScaleX(1);
		setScaleY(1);

		return image; 
	}
}
