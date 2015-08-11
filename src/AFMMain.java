

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Constants.ShapeType;


public class AFMMain extends Application {

	public static VBox root;
	public static Stage stage;
	public static AnchorPane splash;
	public int shapetype = ShapeType.triangle;
	public static Stage popupStage;
	
	/*
	 * Handles to values that need to be kept from different AFMController initializations
	 * All
	 */
	public static ImageView initialTipSelection;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Font.loadFont(AFMMain.class.getResourceAsStream("Fonts/expressway.ttf"), 12);
		root = FXMLLoader.load(getClass().getResource("AFMMainFrontEnd.fxml"));
		stage = primaryStage;
		stage.setResizable(false);

		splash = FXMLLoader.load(getClass().getResource("AFMSplash.fxml"));
		root.getChildren().add(splash);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


	public void begin(){
		AnchorPane runPane;
		try {
			runPane = FXMLLoader.load(getClass().getResource("AFMRun.fxml"));
		} catch (IOException e) {
			runPane = new AnchorPane();
			e.printStackTrace();
		}
		setContent(runPane);
	}

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

	public void cancelRun(){
		showSplash();
	}

	public void showSplash(){
		setContent(splash);
	}

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

	public int getShapeType(){return shapetype;}
	public void setShapeType(int e){shapetype = e;}

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
