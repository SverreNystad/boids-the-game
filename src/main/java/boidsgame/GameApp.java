package boidsgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApp extends Application {
    
    public static void main(String[] args) {
        Application.launch(args);
    }
   
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Boids the game");
        primaryStage.setScene(new Scene(FXMLLoader.load(GameApp.class.getResource("mainMenu.fxml"))));
        // primaryStage.setScene(new Scene(FXMLLoader.load(GameApp.class.getResource("settings.fxml"))));

        primaryStage.show();
    }
    
}
