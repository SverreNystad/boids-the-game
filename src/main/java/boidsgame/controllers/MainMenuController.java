package boidsgame.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController extends Controller{
	@FXML private Button playButton, settingsButton, quitButton, leaderboardButton;

	@FXML 
	private void switchToSettings(ActionEvent event) throws IOException{
		System.out.println("Change to settings");
		switchToScene(event, "settings.fxml");
	}
	@FXML
	private void switchToLeaderboard(ActionEvent event) throws IOException {
		switchToScene(event, "leaderboard.fxml");
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
