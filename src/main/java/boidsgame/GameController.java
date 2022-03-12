package boidsgame;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import javafx.scene.Node;

public class GameController {
	// private Stage stage; 
	// private Scene scene;
	@FXML
	private Button playButton;
	
	@FXML
	private Button settingsButton;

	@FXML
	private Button quitButton;
	
	
	public void switchToMainMenu(ActionEvent event) throws IOException{
	// 	// stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	// 	scene = new Scene(FXMLLoader.load(GameApp.class.getResource("mainMenu.fxml")));
	// 	stage.setScene(scene);
	// 	stage.show();
	}
	public void switchToSettings(ActionEvent event) throws IOException{
	// 	// stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	// 	// stage = (Stage)(settingsButton.getSource()).getScrene().getWindow();

	// 	scene = new Scene(FXMLLoader.load(GameApp.class.getResource("settings.fxml")));
	// 	stage.setScene(scene);
	// 	stage.show();
	}

	public void quitProgram(ActionEvent event) throws IOException{
		// Kills the Program
		Platform.exit();
	}
}
