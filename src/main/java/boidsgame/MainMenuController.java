package boidsgame;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController extends Controller{
	// Main menu
	@FXML private Button playButton;
	@FXML private Button settingsButton;
	@FXML private Button quitButton;

	public void switchToSettings(ActionEvent event) throws IOException{
		System.out.println("Load settings");
		switchToScene(event, "settings.fxml");
	}
	public void switchToPlay(ActionEvent event) throws IOException{
		System.out.println("Start game");
		switchToScene(event, "play.fxml");
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
