package Shapes;

import javafx.scene.paint.Color;

public class ObjectStrut extends javafx.scene.shape.Rectangle {
	
	public ObjectStrut(double initX, double initY, double width, double height, Color color){
		super();
		this.setX(initX);
		this.setY(initY);
		this.setHeight(height);
		this.setWidth(width);
		this.setFill(color);
	}

}
