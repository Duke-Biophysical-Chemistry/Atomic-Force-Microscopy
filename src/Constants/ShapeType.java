package Constants;

import Shapes.Circle;
import Shapes.DeltaFunction;
import Shapes.TallRectangle;
import Shapes.Triangle;
import javafx.scene.shape.Shape;


public abstract class ShapeType {

	public static final int triangle = 0;
	public static final int circle = 1;
	public static final int tallRectangle= 2;
	public static final int delta= 3;

	public static Shape getShapeFromShapetype(int shapetype){
		switch (shapetype){
		case triangle: return new Triangle();
		case circle: return new Circle();
		case tallRectangle: return new TallRectangle();
		default: return new DeltaFunction(); //case delta
		}
	}
}
