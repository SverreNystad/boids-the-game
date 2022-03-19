package boidsgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
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

	@FXML
	private ToggleButton wraparoundButton;

	// Variables
	String gameMode = "Hoid";
	String wraparound = "on";
	int startBoidsAmountSliderValue;
	int startPoidProsentSliderValue;

	public void handleGamemodeSwitch(ActionEvent event) throws IOException{
		gameMode = (rbHoid.isSelected()) ? rbHoid.getText(): rbPoid.getText(); 
	}

	public void handleStartBoidsAmountSlider(MouseEvent event) throws IOException{
		startBoidsAmountSliderValue = (int) Math.floor(startBoidsAmountSlider.getValue());
		startBoidsAmount.setText(Integer.toString(startBoidsAmountSliderValue) + " boids");
		// startBoidsAmount.setText((startBoidsAmountSlider.getValue()) + " boids");

	}

	public void handleStartPoidProsentSlider(MouseEvent event) throws IOException{
		startPoidProsentSliderValue = (int) Math.floor(startPoidProsentSlider.getValue());
		startPoidProsent.setText(Integer.toString(startPoidProsentSliderValue) + "%");
	}
	public void hancleWraparound(ActionEvent event) throws IOException{
		wraparound = (wraparoundButton.isSelected()) ? "off" : "on";
	}
	public void storeSettingsInFile(){
		// TODO: MUST SAVE RELATIV TO PATH AND IN RESOURCES DIR
		try{
			File currentGameSettings = new File("currentGameSettings.txt");
			FileWriter currentWritter = new FileWriter(currentGameSettings);
			// FileWriter currentWritter = new FileWriter("currentGameSettings.txt");
			currentWritter.write("Gamemode, startBoidsAmount, startPoidProsent, wraparound\n");
			currentWritter.append(gameMode + ", " + String.valueOf(startBoidsAmountSliderValue) + ", " + String.valueOf(startPoidProsentSliderValue) + ", " + wraparound);
			currentWritter.close();
		}
		catch (IOException e){
			System.out.println("An error has occured. In the storing of gamesettings.");
		}
	}

	// NOTE: Maight only have one function and buttenName be filename except filtype
	public void switchToMainMenu(ActionEvent event) throws IOException{
		storeSettingsInFile();
		switchToScene(event, "mainMenu.fxml");
	}
	public void switchToSettings(ActionEvent event) throws IOException{
		switchToScene(event, "settings.fxml");
	}
	public void switchToPlay(ActionEvent event) throws IOException{
		switchToScene(event, "play.fxml");
		readFromSettingsfile()
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
