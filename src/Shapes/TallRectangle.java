package Shapes;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class TallRectangle extends javafx.scene.shape.Rectangle {

	public static final Double scaleFactor = 4.0d;
	public static final Double WIDTH = 30.0d;
	public static final double HEIGHT = 60.0d;
	
	public TallRectangle(){
		
		super(WIDTH, HEIGHT);
		
		setFill(Color.GREEN);
		setStroke(Color.BLACK);
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
