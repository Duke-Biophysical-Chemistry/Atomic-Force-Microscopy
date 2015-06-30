import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class TriangleTip extends javafx.scene.shape.Polygon implements ITip{


	public TriangleTip(){
		super();
		getPoints().addAll(new Double[]{
				0.0,0.0,
				30.0,0.0,
				15.0,30.0
		});
		setId("Tip");
		setFill(Color.LIGHTGREEN);
		setStroke(Color.BLACK);
		takeStartingPosition();

	}

	@Override
	public void takeStartingPosition() {
		// TODO Auto-generated method stub
		double startingX = 30;
		double startingY = 230;
		relocate(startingX, startingY);		
	}

	@Override
	public void startTransition() {
		// TODO Auto-generated method stub
		addTriangleTipTransition();
	}

	/**
	 * Add transition to the triangle tip. 
	 * Uses the custom internal interpolator class
	 */
	private void addTriangleTipTransition(){


		/*
		 * https://docs.oracle.com/javafx/2/animations/basics.htm#CJAFADFJ
		 * Add Transition qui
		 */

		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		AnimationTimer timer = new TriangleAnimation();
		Duration duration = Duration.millis(2000);
		
		KeyValue keyValueX = new KeyValue(this.translateXProperty(),2);
		
		EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Finished");
			}
		};
		
		KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
		timer.start();

	}

	private class TriangleAnimation extends AnimationTimer{
		Delta d;
		public TriangleAnimation(){
			d = new Delta(0,0);
		}
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			double deltaVal = 1.0;
			TriangleTip.this.setTranslateX(d.x+deltaVal);
			d.x +=deltaVal;
		}
	}
	private class Delta{
		double x,y, initX, initY;
		public Delta(double initX, double initY){
			this.x=0;
			this.y=0;
			this.initX = initX;
			this.initY = initY;
		}
		}
}