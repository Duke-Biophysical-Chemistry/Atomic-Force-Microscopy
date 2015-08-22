

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Constants.DimensionConstants;
import Constants.ShapeType;
import Shapes.ObjectStrut;
import Shapes.SmoothTrack;


public class AFMMain extends Application {
	/**
	 * MODEL CLASS
	 */

	public static VBox root;
	public static Stage stage;
	public static AnchorPane splash;
	public static int shapetype;
	public static Stage popupStage;

	/*
	 * Handles to values that need to be kept from different AFMController initializations
	 * All
	 */
	public static ImageView initialTipSelection;
	public static Pane runPane;
	public static ScatterChart<Double, Double> runChart;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Font.loadFont(AFMMain.class.getResourceAsStream("Fonts/expressway.ttf"), 12);
		root = FXMLLoader.load(getClass().getResource("AFMMainFrontEnd.fxml"));
		stage = primaryStage;
		stage.setResizable(false);
		shapetype = ShapeType.triangle;
		splash = FXMLLoader.load(getClass().getResource("AFMSplash.fxml"));
		root.getChildren().add(splash);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Methods for AFMRun and associated simulation
	 */

	public void setRun(){
		AnchorPane runPane;
		try {
			runPane = FXMLLoader.load(getClass().getResource("AFMRun.fxml"));
		} catch (IOException e) {
			runPane = new AnchorPane();
			e.printStackTrace();
		}
		setContent(runPane);
	}
	/*Setters for runCanvas and runChart, called just before beginRun() in AFMController*/
	public void setRunPane(Pane p){runPane = p;}
	public void setRunChart(ScatterChart<Double,Double> c){runChart = c;}
	
	public void loadRun(){
		if (runPane == null | runChart == null){
			System.err.println("NullPointerException on beginRun() in AFMMain");
			return;
		}
		System.out.println("Loading resources");
		loadTrack();
		loadShape();
		loadScatter();
	}
	public void loadShape(){
		Shape currentShape = ShapeType.getShapeFromShapetype(shapetype);
		currentShape.setLayoutX(DimensionConstants.initialX);
		double initialY = DimensionConstants.getInitialY(shapetype);
		/*
		 * nestles shape neatly on track since getInitialY is used for both
		 * assembling track and placing shape. 
		 */
		double reconciledY = initialY + (DimensionConstants.reconcileHeight(shapetype));
		currentShape.setLayoutY(reconciledY);
		currentShape.setFill(Color.RED);
		runPane.getChildren().add(currentShape);
//		System.out.println(currentShape.getLayoutBounds().getWidth());
//		System.out.println(currentShape.getLayoutBounds().getHeight());
	}
	public void loadTrack(){
		if(DimensionConstants.isRough){
			loadRoughTrack();
		}
		else{
			loadSmoothTrack();
		}
	}
	
	private void loadRoughTrack(){
		
	}
	
	private void loadSmoothTrack(){
		//define constants
		
		//init smooth track
		final double initY = DimensionConstants.getInitialY(shapetype) + DimensionConstants.getShapeHeight(shapetype);
		final double trackWidth = DimensionConstants.PANE_WIDTH-DimensionConstants.getInitialX(shapetype);
		final double smoothDistance = trackWidth - DimensionConstants.getObjectWidth();
		final double individualTrackLength = smoothDistance/2d;
		
		//calculate smooth track parameters
		final double leftInitX = DimensionConstants.getInitialX(shapetype);
		final double rightInitX = DimensionConstants.getInitialX(shapetype) + 
				+ individualTrackLength + DimensionConstants.getObjectWidth();
		final Color trackColor = Color.DIMGRAY;
		
		//init object top
		final double topWidth = DimensionConstants.getObjectWidth() + DimensionConstants.trackDepth;
		final double topInitX = leftInitX + individualTrackLength;
		final double topInitY = initY - DimensionConstants.getObjectHeight();
		final Color topColor = Color.LIGHTCORAL;
		
		//init object struts
		final double strutHeight = DimensionConstants.getObjectHeight();
		final double leftStrutInitX = leftInitX + individualTrackLength;
		final double rightStrutInitX = leftInitX + individualTrackLength + DimensionConstants.getObjectWidth();
		final double strutInitY = initY - strutHeight + DimensionConstants.trackDepth;
		final Color strutColor = topColor;

		//construct track, struts, and top
		final Rectangle leftTrack = new SmoothTrack(leftInitX, initY, individualTrackLength,DimensionConstants.trackDepth, trackColor);
		final Rectangle rightTrack = new SmoothTrack(rightInitX, initY, individualTrackLength, DimensionConstants.trackDepth, trackColor);
		final Rectangle top = new SmoothTrack(topInitX, topInitY, topWidth, DimensionConstants.trackDepth, topColor);
		final Rectangle leftObjectStrut = new ObjectStrut(leftStrutInitX, strutInitY, DimensionConstants.trackDepth, strutHeight, strutColor);
		final Rectangle rightObjectStrut = new ObjectStrut(rightStrutInitX, strutInitY, DimensionConstants.trackDepth, strutHeight, strutColor);
		runPane.getChildren().addAll(leftTrack, rightTrack, top, leftObjectStrut, rightObjectStrut);
	}
	
	public void loadScatter(){
		final double delta = 0.1; //scatter point increment value
	}
	
	public void beginRun(){
		System.out.println("Run is starting!");
	}
	
	public void showSplash(){
		setContent(splash);
	}
	
	/**
	 * Methods for Splash Buttons AND menubar
	 */

	public void exit(){
		Platform.exit();
	}

	public void restart(){
		stage.close();
		try{
			start(new Stage());
		}
		catch (Exception e){
			e.printStackTrace();
			exit();
		}
	}

	/**
	 * Methods for Changing selected shapes
	 */

	/*Starts the changeShapes dialog. ShapeChooserController is then used to change the current shape.*/
	public void changeShapes(){
		popupStage = new Stage();
		popupStage.initModality(Modality.APPLICATION_MODAL);
		try {
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AFMShapeChooser.fxml")));
			popupStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		popupStage.showAndWait();
	}
	public void closePopup(){
		popupStage.close();
	}

	/**
	 * Changes the shape of the initialTipImage ImageView window
	 * @param image - WritableImage to insert
	 * @param width - width of imageview
	 * @param height - height of imageview
	 */
	public void setInitialTipImage(WritableImage image, double width, double height){
		Platform.runLater(()->{
			if(initialTipSelection != null){
				AFMMain.initialTipSelection.setImage(image);
				AFMMain.initialTipSelection.setFitWidth(width);
				AFMMain.initialTipSelection.setFitHeight(height);
			}
		});
	}

	/*Getter/Setter for shapetype*/
	public int getShapeType(){return shapetype;}
	public void setShapeType(int e){shapetype = e;}

	/**
	 * sets the content of the program (the second Pane)
	 * @param content
	 */
	
	public void setContent(Pane content){
		Platform.runLater(()->{
			root.getChildren().remove(1);
			root.getChildren().add(content);
		});
	}


	public static void main(String[] args){
		launch(args);
	}
}
