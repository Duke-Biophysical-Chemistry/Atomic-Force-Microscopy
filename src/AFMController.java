
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AFMController extends AFMMain implements Initializable {

	public final SimpleBooleanProperty IS_RUNNING = new SimpleBooleanProperty(false);

	/*For countdown before run starts*/
	protected Label countdownTimerLabel;
	protected static final Integer COUNTDOWN_TIMER_MAX = 3;
	protected static IntegerProperty COUNTDOWN_TIMER_CURRENT;
	protected Timeline timeline;

	@FXML
	public Menu fileMenu, helpMenu;

	@FXML
	public MenuItem restartItem, quitItem, courseSite, About;

	@FXML
	public BorderPane tipPane;

	@FXML
	public ScatterChart<Double, Double> Chart;

	@FXML
	public ImageView initialTip;

	@FXML
	public Pane runPane;

	@FXML
	public Button exitButton, startButton, CancelRun, SaveRun, runSimulation;

	@FXML
	public StackPane RunStackPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setRunningListener();
	}

	public void initButtons(){
	}

	public void goToSite(String site){
		try {
			Desktop.getDesktop().browse(new URI(site));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goToSourceCode(){
		final String url = "https://www.github.com/Duke-Biophysical-Chemistry/Atomic-Force-Microscopy";
		goToSite(url);
	}

	public void goToCourseSite(){
		final String url = "https://www.sakai.duke.edu";
		goToSite(url);
	}

	public void exit(){
		super.exit();
	}

	public void restart(){
		super.restart();
	}

	public void begin(){
		super.setRun();
	}

	public void doSimulation(){
		//setup simulation (pulse countdown, etc.)
		//set psuedo-final objects
		super.setRunPane(runPane);
		super.setRunChart(Chart);
		//start loading while splash countdown is active
		super.loadRun();
		//GUI updates and splash
		Platform.runLater(()->{
			runSimulation.setVisible(false);
			IS_RUNNING.set(true);
			initCountdownTimer();
			timeline.setOnFinished((e)->{
				countdownTimerLabel.setVisible(false); //remove from scene
				super.beginRun();//start simulation found in AFMMain
			});
			startCountdownTimer();
		});//end runLater
	}

	public void changeShapes(){
		System.out.println("Clicked");
		super.changeShapes();
	}

	public void cancelRun(){
		IS_RUNNING.set(false);
		timeline.stop();
		super.showSplash();
	}

	public void saveRun(){

	}

	/**
	 * Initialize CountdownTimer
	 */
	protected void initCountdownTimer(){
		COUNTDOWN_TIMER_CURRENT = new SimpleIntegerProperty(COUNTDOWN_TIMER_MAX);

		countdownTimerLabel = new Label();
		countdownTimerLabel.setText(COUNTDOWN_TIMER_MAX.toString());
		countdownTimerLabel.setTextFill(Color.GREEN);
		countdownTimerLabel.setStyle("-fx-font-size: 4em");
		countdownTimerLabel.textProperty().bind(COUNTDOWN_TIMER_CURRENT.asString());

		timeline = new Timeline();
		RunStackPane.getChildren().add(countdownTimerLabel);
	}

	/**
	 * Start CountdownTimer
	 * Altered from http://asgteach.com/2011/10/javafx-animation-and-binding-simple-countdown-timer-2/
	 */
	protected void startCountdownTimer(){
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(COUNTDOWN_TIMER_MAX),
						new KeyValue(COUNTDOWN_TIMER_CURRENT, 0))); // decrement by 1 from current -> 0
		timeline.playFromStart();
	}

	/**
	 * Actions that are performed when IS_RUNNING is changed from FALSE -> TRUE and TRUE-> FALSE
	 */
	protected void setRunningListener(){
		IS_RUNNING.addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(oldValue == false && newValue == true){//simulation is beginning
					SaveRun.setDisable(true);//prevent saving during run
				}
				else if (oldValue == true && newValue == false){//simulation is ending
					SaveRun.setDisable(true);//allow saving
					timeline.stop();
				}
			}
		});
	}
}
