package boidsgame.controllers;

import java.io.IOException;

import boidsgame.Filehandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class leaderboardController extends Controller{
	@FXML private Button mainMenuButton;
	@FXML private Label poidRunsLabel, hoidRunsLabel;

	
	@FXML
	private void initialize() {
		try {
			poidRunsLabel.setText(Filehandler.formatScores(Filehandler.sortHighscoreByGamemodeValue(new Filehandler().readFromHighscoreFile()).get(0)));
			hoidRunsLabel.setText(Filehandler.formatScores(Filehandler.sortHighscoreByGamemodeValue(new Filehandler().readFromHighscoreFile()).get(1)));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	private void switchToMainMenu(ActionEvent event) throws IOException {
		switchToScene(event, "mainMenu.fxml");
	}
}
