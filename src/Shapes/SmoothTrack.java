package Shapes;

import Constants.ShapeType;
import javafx.scene.paint.Color;

public class SmoothTrack extends javafx.scene.shape.Rectangle{

	public final double WIDTH;
	public final double HEIGHT;
	public final double STROKE_WIDTH = 0.0d;
	
	public SmoothTrack(double initX, double initY, double width, double height, Color color){
		super();
		this.WIDTH = width;
		this.HEIGHT = height;
		this.setX(initX);
		this.setY(initY);
		this.setStrokeWidth(STROKE_WIDTH);
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		this.setFill(color);
	}
	
}
