package boidsgame;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

// import javafx.scene.Scene;
// import javafx.stage.Stage;

public class GameController {
	private Stage stage; 
	private Scene scene;
	// Main menu
	@FXML
	private Button playButton;
	
	@FXML
	private Button settingsButton;

	@FXML
	private Button quitButton;
	
	// Settings
	// final Label
	@FXML
	private Slider startBoidsAmountSlider;

	@FXML
	private Label startBoidsAmount;
	
	@FXML
	private Slider startPoidProsentSlider;
	
	@FXML
	private Label startPoidProsent;
	
	@FXML
	private RadioButton rbHoid;

	@FXML
	private RadioButton rbPoid;

	// Variables
	String GameMode;

	public void handleGamemodeSwitch(ActionEvent event) throws IOException{
		// TODO find a way to save info and use GameMode
		GameMode = (rbHoid.isSelected()) ? rbHoid.getText(): rbPoid.getText(); 
	}

	public void handleStartBoidsAmountSlider(ActionEvent event) throws IOException{
		int startBoidsAmountSliderValue = (int) Math.floor(startBoidsAmountSlider.getValue());
		startBoidsAmount.setText(Integer.toString(startBoidsAmountSliderValue) + " boids");
		// startBoidsAmount.setText((startBoidsAmountSlider.getValue()) + " boids");
		System.out.println(startBoidsAmountSlider.getValue());

	}

	public void handleStartPoidProsentSlider(ActionEvent event) throws IOException{
		int startPoidProsentSliderValue = (int) Math.floor(startBoidsAmountSlider.getValue());
		startBoidsAmount.setText(Integer.toString(startPoidProsentSliderValue) + "%");

	}

	// NOTE: Maight only have one function and buttenName be filename except filtype
	public void switchToMainMenu(ActionEvent event) throws IOException{
		switchToScene(event, "mainMenu.fxml");
	}
	public void switchToSettings(ActionEvent event) throws IOException{
		switchToScene(event, "settings.fxml");
	}
	private void switchToScene(ActionEvent event, String filename) throws IOException{
		// stage = (Stage)(settingsButton.getSource()).getScrene().getWindow();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(FXMLLoader.load(GameApp.class.getResource(filename)));
		stage.setScene(scene);
		stage.show();
	}

	public void quitProgram(ActionEvent event) throws IOException{
		// Kills the Program
		Platform.exit();
	}
}
