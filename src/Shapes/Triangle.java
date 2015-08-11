package Shapes;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class Triangle extends javafx.scene.shape.Polygon {
	
	public static final Double scaleFactor = 4.0d;
	
	public Triangle(){
		super();
		getPoints().addAll(new Double[]{
				0.0,0.0,
				30.0,0.0,
				15.0,30.0
		});
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
