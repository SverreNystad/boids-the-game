package boidsgame;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
	private Stage stage; 
	private Scene scene;
	// Main menu
	@FXML private Button playButton;
	@FXML private Button settingsButton;
	@FXML private Button quitButton;

	public void switchToSettings(ActionEvent event) throws IOException{
		System.out.println("Load settings");
		switchToScene(event, "settings.fxml");
	}
	public void switchToPlay(ActionEvent event) throws IOException{
		switchToScene(event, "play.fxml");
		List<String> settings = Filehandler.readFromSettingsfile();
		System.out.println(settings);
		int canvasLength = 1280; //(int) worldCanvas.getWidth() TODO: Could get values from canvas
		int canvasHeight = 700;  //(int) worldCanvas.getHeight() TODO: Could get values from canvas
		World gameWorld = World.initGame(canvasLength, canvasHeight, settings.get(4), (int) Integer.valueOf(settings.get(5)),(int) Integer.valueOf(settings.get(6)), (settings.get(7)).equals("on"));
		GraphicsContext gc = worldCanvas.getGraphicsContext2D(); // TODO: HVOR GC blir definert

		runGame();
	}
	/**
	 * A general switch to scene method. It takes in the filename and changes scene to current scene.
	 * @param event ActionEvent fired by the FXML.
	 * @param filename is a string that represent the filename.
	 * @throws IOException
	 */
	private void switchToScene(ActionEvent event, String filename) throws IOException{
		// stage = (Stage)(settingsButton.getSource()).getScrene().getWindow();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(FXMLLoader.load(GameApp.class.getResource(filename)));
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Kills the Program
	 * @param event
	 * @throws IOException
	 */
	public void quitProgram(ActionEvent event) throws IOException{
		Platform.exit();
	}
}
