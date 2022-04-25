package boidsgame.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController extends Controller{
	// Main menu
	@FXML private Button playButton;
	@FXML private Button settingsButton;
	@FXML private Button quitButton;

	@FXML 
	private void switchToSettings(ActionEvent event) throws IOException{
		System.out.println("Load settings");
		switchToScene(event, "settings.fxml");
	}
	@FXML 
	private void switchToPlay(ActionEvent event) throws IOException{
		System.out.println("Start game");
		switchToScene(event, "play.fxml");
	}

	/**
	 * Kills the Program
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	private void quitProgram(ActionEvent event) throws IOException{
		Platform.exit();
	}
}
