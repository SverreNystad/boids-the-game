package boidsgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class GameApp extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
   
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Boids the Game");
		primaryStage.setScene(new Scene(FXMLLoader.load(GameApp.class.getResource("mainMenu.fxml"))));
		primaryStage.setResizable(false);
		Image gameLogo = new Image(getClass().getResourceAsStream("boidsLogo.png"));
		primaryStage.getIcons().add(gameLogo);
		primaryStage.show();
	}
	
}
