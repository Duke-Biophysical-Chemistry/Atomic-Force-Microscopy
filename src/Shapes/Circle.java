package Shapes;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class Circle extends javafx.scene.shape.Circle {

	public static final Double scaleFactor = 4.0d;
	public static final Double RADIUS = 15d;
	
	public Circle(){
		
		super(RADIUS);
		
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
