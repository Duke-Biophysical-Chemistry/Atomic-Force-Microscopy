package Constants;

import javafx.beans.property.SimpleDoubleProperty;
import Shapes.Circle;
import Shapes.DeltaFunction;
import Shapes.TallRectangle;
import Shapes.Triangle;

public abstract class DimensionConstants {
	
	/*
	 * Constants used for setInitialTipImage in AFMController
	 * These are all IMAGE heights, not necessarily SHAPE heights
	 */
	public static final double DeltaWidth = 15;
	public static final double AllOtherWidths = 50;
	
	public static final double TRIANGLE_HEIGHT = 66;
	public static final double CIRCLE_HEIGHT = 50;
	public static final double TALL_RECTANGLE_HEIGHT = 100;
	public static final double DELTA_HEIGHT = 100;
	
	/*
	 * Constants used for 
	 * setting the run pane
	 */

	public static final double CONTENT_HEIGHT = 371;
	public static final double CONTENT_WIDTH = 636;

	public static final double PANE_HEIGHT = 178;
	public static final double PANE_WIDTH = 629;
	
	public static double initialX = 0.0d;
	
	/*
	 * Constants used to define the track
	 */
	
	public static boolean isRough = false; //adds rough nature to track (bumpy)
	public static double trackDepth = 2.0d; //height of track, valued via heuristics
	public static final SimpleDoubleProperty objectHeight = new SimpleDoubleProperty(40.0d); //height of scanned object
	public static final SimpleDoubleProperty objectWidth = new SimpleDoubleProperty(50.0d); //width of scanned object
	
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
	
	public static final double reconcileHeight(int shapeType){
		if (shapeType == ShapeType.circle) return ShapeType.CIRCLE_RADIUS;
		return 0;
	}
	
	/*
	 * Methods to return SHAPE height and width from shapetype input
	 */
	public static final double getShapeHeight(int shapeType){
		double height = 0.0d;
		if(shapeType == ShapeType.triangle) height = new Triangle().getLayoutBounds().getHeight();
		else if(shapeType == ShapeType.circle) height = new Circle().getLayoutBounds().getHeight();
		else if(shapeType == ShapeType.tallRectangle) height = new TallRectangle().getLayoutBounds().getHeight();
		else height = new DeltaFunction().getLayoutBounds().getHeight();
		return height;
	}
	
	public static final double getShapeWidth(int shapeType){
		double width = 0.0d;
		if(shapeType == ShapeType.triangle) width = new Triangle().getLayoutBounds().getWidth();
		else if(shapeType == ShapeType.circle) width = new Circle().getLayoutBounds().getWidth();
		else if(shapeType == ShapeType.tallRectangle) width = new TallRectangle().getLayoutBounds().getWidth();
		else if (shapeType == ShapeType.delta) width = new DeltaFunction().getLayoutBounds().getWidth();
		return width;
	}
	
	
	/*
	 * Methods used to return Image height and width from ShapeType input
	 */
	
	public static final double getImageHeight(int shapeType){
		if(shapeType == ShapeType.triangle) return TRIANGLE_HEIGHT;
		else if(shapeType == ShapeType.circle) return CIRCLE_HEIGHT;
		else if(shapeType == ShapeType.tallRectangle) return TALL_RECTANGLE_HEIGHT;
		else return DELTA_HEIGHT;
	}
	public static final double getImageWidth(int shapeType){
		if(shapeType == ShapeType.delta) return DeltaWidth;
		else return AllOtherWidths;
	}
	
	/*
	 * Getter / setter methods for object dimensions
	 */
	//isRough
	public static void setRough (boolean roughNature){
		isRough = roughNature;
	}
	public static boolean getRough(){return isRough;}
	
	//objectHeight
	public static void setObjectHeight(double newHeight){
		objectHeight.set(newHeight);
	}
	public static SimpleDoubleProperty getObjectHeightProperty(){return objectHeight;}
	public static double getObjectHeight(){return objectHeight.get();}
	
	//objectWidth
	public static void setObjectWidth(double newWidth){
		objectWidth.set(newWidth);
	}
	public static SimpleDoubleProperty getObjectWidthProperty(){return objectWidth;}
	public static double getObjectWidth(){return objectWidth.get();}
	
	
}
