import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class AFMTrackPane extends javafx.scene.layout.AnchorPane{

	/*
	 * Accesses stackpane constructor and adjusts the scene's size
	 */
	public AFMTrackPane(Pane parent){
		super();
		double preferredWidth = parent.getWidth();
		double preferredHeight = parent.getWidth();
		setPrefSize(preferredWidth, preferredHeight);
		setId("AFMTrackPane");
	}

	public ITip getTip(){
		return (ITip) lookup("#Tip");
	}

	public void addTip(TipType type)
	{	
		switch (type)
		{
		case TRIANGLE_TIP:
			addTriangleTip();
			break;
		}
	}

	private void addTriangleTip(){
		TriangleTip tip = new TriangleTip();
		this.getChildren().add(tip);
	}

}
