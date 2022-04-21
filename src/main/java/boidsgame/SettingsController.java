package boidsgame;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;


public class SettingsController extends Controller {
	// private Stage stage; 
	// private Scene scene;
	
	@FXML private Slider startBoidsAmountSlider;
	@FXML private Label startBoidsAmount;
	@FXML private Slider startPoidProsentSlider;
	@FXML private Label startPoidProsent;
	@FXML private RadioButton rbHoid;
	@FXML private RadioButton rbPoid;
	@FXML private ToggleButton wraparoundButton;

	private String gameMode = "Hoid";
	private String wraparound = "on";
	private int startBoidsAmountSliderValue;
	private int startPoidProsentSliderValue;
	// Settings controller
	/**
	 * handleGamemodeSwitch is called when one click the button in settings. It checks what gameMode the player wants to play
	 */
	@FXML
	private void handleGamemodeSwitch(ActionEvent event) throws IOException {
		gameMode = (rbHoid.isSelected()) ? rbHoid.getText() : rbPoid.getText();
	}
	@FXML
	private void handleStartBoidsAmountSlider(MouseEvent event) throws IOException {
		startBoidsAmountSliderValue = (int) Math.floor(startBoidsAmountSlider.getValue());
		startBoidsAmount.setText(Integer.toString(startBoidsAmountSliderValue) + " boids");
		// startBoidsAmount.setText((startBoidsAmountSlider.getValue()) + " boids");
	}
	@FXML
	private void handleStartPoidProsentSlider(MouseEvent event) throws IOException {
		startPoidProsentSliderValue = (int) Math.floor(startPoidProsentSlider.getValue());
		startPoidProsent.setText(Integer.toString(startPoidProsentSliderValue) + "%");
	}
	@FXML
	private void handleWraparound(ActionEvent event) throws IOException {
		wraparound = (wraparoundButton.isSelected()) ? "off" : "on";
	}
	@FXML
	private void switchToMainMenu(ActionEvent event) throws IOException {
		Filehandler.storeSettingsInFile(gameMode, startBoidsAmountSliderValue, startPoidProsentSliderValue, wraparound); // Saves settings
		switchToScene(event, "mainMenu.fxml");
	}
}
