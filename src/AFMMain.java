import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AFMMain extends Application {

	public static VBox root;
	public static Stage stage;
	public static AnchorPane splash;
	public static ShapeEnum shapetype = ShapeEnum.triangle;

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
	
	public void changeShapes(){
		Stage modalDialog = new Stage();
		modalDialog.initModality(Modality.APPLICATION_MODAL);
		try {
			modalDialog.setScene(FXMLLoader.load(getClass().getResource("AFMShapeChooser.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		modalDialog.show();
	}
	
	public ShapeEnum getShapeType(){return shapetype;}
	public void setShapeType(ShapeEnum e){shapetype = e;}

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
