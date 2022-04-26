package boidsgame.controllers;

import java.io.IOException;

import boidsgame.Filehandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;



public class SettingsController extends Controller {
	@FXML private Slider startBoidsAmountSlider;
	@FXML private Label startBoidsAmount;
	@FXML private Slider startPoidProsentSlider;
	@FXML private Label startPoidProsent;
	@FXML private RadioButton rbHoid;
	@FXML private RadioButton rbPoid;
	@FXML private ToggleButton wraparoundButton;

	@FXML private Slider poidViewRangeSlider;
	@FXML private Slider killRadiusSlider;
	@FXML private Slider poidSeperationCoefficientSlider;
	@FXML private Slider AttractionToHoidsCoefficientSlider;
	@FXML private Slider hoidViewRangeSlider; 
	@FXML private Slider CohesionCoefficientSlider;
	@FXML private Slider AlignmentCoefficientSlider;
	@FXML private Slider hoidSeperationCoefficientSlider;



	private String gameMode = "Hoid";
	private String wraparound = "on";
	private int startBoidsAmountSliderValue;
	private int startPoidProsentSliderValue;

	private int poidViewRangeSliderValue = 60;
	private int killRadiusSliderValue = 5;
	private double poidSeperationCoefficientValue = 1;
	private double AttractionToHoidsCoefficientValue = 1;
	private int hoidViewRangeSliderValue = 40;
	private double CohesionCoefficientValue = 1;
	private double AlignmentCoefficientValue = 1;
	private double hoidSeperationCoefficientValue = 1;
	
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
	// POID:
	@FXML
	private void handlePoidViewRange(ActionEvent event) throws IOException {
		poidViewRangeSliderValue = (int) Math.floor(poidViewRangeSlider.getValue());
	}
	@FXML
	private void handlePoidSettings(ActionEvent event) throws IOException {
		// poidViewRangeSliderValue = (int) Math.floor(poidViewRangeSlider.getValue());
		killRadiusSliderValue = (int) Math.floor(killRadiusSlider.getValue());
		poidSeperationCoefficientValue = poidSeperationCoefficientSlider.getValue();
		AttractionToHoidsCoefficientValue = AttractionToHoidsCoefficientSlider.getValue();
	}

	@FXML
	private void handleHoidSettings(ActionEvent event) throws IOException {
		hoidViewRangeSliderValue = (int) Math.floor(hoidViewRangeSlider.getValue());
		CohesionCoefficientValue = CohesionCoefficientSlider.getValue();
		AlignmentCoefficientValue = AlignmentCoefficientSlider.getValue();
		hoidSeperationCoefficientValue = hoidSeperationCoefficientSlider.getValue();
	}

	@FXML
	private void switchToMainMenu(ActionEvent event) throws IOException {
		Filehandler.storeSettingsInFile(gameMode, startBoidsAmountSliderValue, startPoidProsentSliderValue, wraparound); // Saves settings
		// Filehandler.storeSettingsInFile(
		// 	gameMode, startBoidsAmountSliderValue, startPoidProsentSliderValue, wraparound,
		// 	poidViewRangeSliderValue, killRadiusSliderValue, poidSeperationCoefficientValue, AttractionToHoidsCoefficientValue,
		// 	hoidViewRangeSliderValue, CohesionCoefficientValue, AlignmentCoefficientValue, hoidSeperationCoefficientValue
		// 	); // Saves settings
		System.out.println(startBoidsAmountSliderValue + " " + startPoidProsentSliderValue + " " + wraparound + " " +
			poidViewRangeSliderValue + " " + killRadiusSliderValue + " " + poidSeperationCoefficientValue + " " + AttractionToHoidsCoefficientValue + " " +
			hoidViewRangeSliderValue + " " + CohesionCoefficientValue + " " + AlignmentCoefficientValue + " " + hoidSeperationCoefficientValue);
		System.out.println("Saved settings.");
		switchToScene(event, "mainMenu.fxml");
	}

}
