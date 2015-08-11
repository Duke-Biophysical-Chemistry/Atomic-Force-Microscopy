package Constants;

import Shapes.Circle;
import Shapes.DeltaFunction;
import Shapes.TallRectangle;
import Shapes.Triangle;

public abstract class DimensionConstants {
	
	/*
	 * Constants used for setInitialTipImage in AFMController
	 */
	public static final double DeltaWidth = 15;
	public static final double AllOtherWidths = 50;
	
	public static final double TRIANGLE_HEIGHT = 66;
	public static final double CIRCLE_HEIGHT = 50;
	public static final double TALL_RECTANGLE_HEIGHT = 100;
	public static final double DELTA_HEIGHT = 100;
	
	/*
	 * Constants used for setting the run pane
	 */

	public static final double CONTENT_HEIGHT = 371;
	public static final double CONTENT_WIDTH = 636;

	public static final double PANE_HEIGHT = 178;
	public static final double PANE_WIDTH = 629;
	
	public static double initialX = 0.0d;
	
	/*
	 * Methods used to return initialX and initalY from shapetype input
	 */
	public static final double getInitialX(int shapeType){
		return 0d;
	}
	public static final double getInitialY(int shapeType){
		double initialYCoord = PANE_HEIGHT;
		if(shapeType == ShapeType.triangle) initialYCoord -= new Triangle().getLayoutBounds().getHeight();
		else if(shapeType == ShapeType.circle) initialYCoord -= new Circle().getLayoutBounds().getHeight();
		else if(shapeType == ShapeType.tallRectangle) initialYCoord -= new TallRectangle().getLayoutBounds().getHeight();
		else initialYCoord -= new DeltaFunction().getLayoutBounds().getHeight();
		return initialYCoord;
	}
	
	
	/*
	 * Methods used to return height and width from ShapeType input
	 */
	
	public static final double getHeight(int shapeType){
		if(shapeType == ShapeType.triangle) return TRIANGLE_HEIGHT;
		else if(shapeType == ShapeType.circle) return CIRCLE_HEIGHT;
		else if(shapeType == ShapeType.tallRectangle) return TALL_RECTANGLE_HEIGHT;
		else return DELTA_HEIGHT;
	}
	public static final double getWidth(int shapeType){
		if(shapeType == ShapeType.delta) return DeltaWidth;
		else return AllOtherWidths;
	}
}
