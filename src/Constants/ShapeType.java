package Constants;

import Shapes.Circle;
import Shapes.DeltaFunction;
import Shapes.TallRectangle;
import Shapes.Triangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public abstract class ShapeType {
	
	/*Initialized Enum of shapes*/
	public static final int triangle = 0;
	public static final int circle = 1;
	public static final int tallRectangle= 2;
	public static final int delta= 3;
	//SHAPE_NAMES correlate shapetype values to their string names
	public static final String[] SHAPE_NAMES = new String[]{"Triangle", "Circle", "Tall Rectangle", "Delta Function"};
	
	/*Universal Constants*/
	public static final Color SHAPE_COLOR = Color.RED;
	public static final Color STROKE_COLOR = Color.BLACK;
	
	/*Constants unique to shapetype objects*/
	public static final double CIRCLE_RADIUS = 15d;

	public static Shape getShapeFromShapetype(int shapetype){
		switch (shapetype){
		case triangle: return new Triangle();
		case circle: return new Circle();
		case tallRectangle: return new TallRectangle();
		default: return new DeltaFunction(); //case delta
		}
	}
}
