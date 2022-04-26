package boidsgame.controllers;

import java.io.IOException;
import boidsgame.Filehandler;
import boidsgame.SettingsContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;



public class SettingsController extends Controller {
	@FXML private Slider startBoidsAmountSlider, startPoidProsentSlider;
	@FXML private Label startBoidsAmount, startPoidProsent;
	@FXML private RadioButton rbHoid, rbPoid;
	@FXML private ToggleButton wraparoundButton;

	@FXML private Slider poidViewRangeSlider, killRadiusSlider, poidSeperationCoefficientSlider, AttractionToHoidsCoefficientSlider;
	@FXML private Slider hoidViewRangeSlider, CohesionCoefficientSlider, AlignmentCoefficientSlider, hoidSeperationCoefficientSlider;


	private SettingsContainer settings;
	@FXML
	private void initialize(){
		settings = new SettingsContainer();
	}
	
	/**
	 * handleGamemodeSwitch is called when one click the button in settings. It checks what gameMode the player wants to play
	 */
	@FXML
	private void handleGamemodeSwitch(ActionEvent event) throws IOException {
		settings.setGameMode((rbHoid.isSelected()) ? rbHoid.getText() : rbPoid.getText());
	}
	@FXML
	private void handleStartBoidsAmountSlider(MouseEvent event) throws IOException {
		settings.setStartBoidsAmountSliderValue((int) Math.floor(startBoidsAmountSlider.getValue()));
		startBoidsAmount.setText(Integer.toString(settings.getStartBoidsAmountSliderValue()) + " boids");
	}
	@FXML
	private void handleStartPoidProsentSlider(MouseEvent event) throws IOException {
		settings.setStartPoidProsentSliderValue((int) Math.floor(startPoidProsentSlider.getValue()));
		startPoidProsent.setText(Integer.toString(settings.getStartPoidProsentSliderValue()) + "%");
	}
	@FXML
	private void handleWraparound(ActionEvent event) throws IOException {
		settings.setWraparound((wraparoundButton.isSelected()) ? "off" : "on");
	}
	// POID:
	@FXML
	private void handlePoidSettings(MouseEvent event) throws IOException {
		settings.setPoidViewRangeSliderValue((int) Math.floor(poidViewRangeSlider.getValue()));
		settings.setKillRadiusSliderValue((int) Math.floor(killRadiusSlider.getValue()));
		settings.setPoidSeperationCoefficientValue(poidSeperationCoefficientSlider.getValue());
		settings.setAttractionToHoidsCoefficientValue(AttractionToHoidsCoefficientSlider.getValue());
	}

	@FXML
	private void handleHoidSettings(MouseEvent event) throws IOException {
		settings.setHoidViewRangeSliderValue((int) Math.floor(hoidViewRangeSlider.getValue()));
		settings.setCohesionCoefficientValue(CohesionCoefficientSlider.getValue());
		settings.setAlignmentCoefficientValue(AlignmentCoefficientSlider.getValue());
		settings.setHoidSeperationCoefficientValue(hoidSeperationCoefficientSlider.getValue());
	}

	@FXML
	private void switchToMainMenu(ActionEvent event) throws IOException {
		// Filehandler.storeSettingsInFile(settings.getGameMode(), settings.getStartBoidsAmountSliderValue(), settings.getStartPoidProsentSliderValue(), settings.getWraparound()); // Saves settings
		Filehandler.storeSettingsInFile(
			settings.getGameMode(), settings.getStartBoidsAmountSliderValue(), settings.getStartPoidProsentSliderValue(), settings.getWraparound(),
			settings.getPoidViewRangeSliderValue(), settings.getKillRadiusSliderValue(), settings.getPoidSeperationCoefficientValue(), settings.getAttractionToHoidsCoefficientValue(),
			settings.getHoidViewRangeSliderValue(), settings.getCohesionCoefficientValue(), settings.getAlignmentCoefficientValue(), settings.getHoidSeperationCoefficientValue()
			); // Saves settings

		System.out.println(settings.toString());
		System.out.println("Saved settings.");
		switchToScene(event, "mainMenu.fxml");
	}
}
