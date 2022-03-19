package boidsgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
// import java.awt.*;
import java.util.Collection;
import java.util.Collections;

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
import javafx.scene.canvas.Canvas;
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

	@FXML
	private Canvas worldCanvas;

	// Variables
	private String gameMode = "Hoid";
	private String wraparound = "on";
	private int startBoidsAmountSliderValue;
	private int startPoidProsentSliderValue;
	private Collection<BoidsInterface> allInitBoids = new ArrayList<>();
	private World gameWorld;

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
		// readFromSettingsfile();
		// TEST
		initGame("Hoid", 200, 20, false);
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

	// GAME methods:

	public void initGame(String gameMode, int startBoidsAmount, int startPoidProsent, Boolean wraparound ) {
		// Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		// Must add all player boids
		int canvasLength = 1280;
		int canvasHeight = 700;
		gameWorld = new World(canvasLength, canvasHeight, allInitBoids);
		allInitBoids.add(new PlayerBoid());
		// Must make
		int poidAmount = (int) Math.floor((startBoidsAmount) * startPoidProsent/100);
		int hoidAmount = startBoidsAmount - poidAmount;
		// Want boids to spawn randomly
		for (int i = 1; i < startBoidsAmount; i++){

			int currentPositionX = (int) Math.floor(Math.random()*canvasLength);
			int currentPositionY = (int) Math.floor(Math.random()*canvasHeight);
			int currentVelocityX = (int) Math.floor(Math.random()*10);
			int currentVelocityY = (int) Math.floor(Math.random()*10);
			if (i < poidAmount){
				allInitBoids.add(new Poid(new Vector(currentPositionX, currentPositionY), new Vector(currentVelocityX, currentVelocityY), new Vector(0, 0), 20, 20, 50, true, gameWorld, 5, 1));
			}
			else{
				allInitBoids.add(new Hoid(new Vector(currentPositionX, currentPositionY), new Vector(currentVelocityX, currentVelocityY), new Vector(0, 0), 20, 20, 50, true, gameWorld, 1, 1, 1));
			}
		}
		gameWorld.setAllInitBoids(allInitBoids);
		System.out.println("Initialization successful");
	}
	
	public void runGame() {
		moveAllBoids();
		drawBoidsOnCanvas();
		
	}
	public void drawBoidsOnCanvas(){
		// TODO FIND A WAY TO DRAW ON SCREEN.
		// worldCanvas.pain
		// worldCanvas.
		// scene.fill
		// gameWorld.allInitBoids;
	}
	public void moveAllBoids(){

	}
	// public static void main(String[] args) {
	// 	GameController test = new GameController();
	// 	test.initGame("Hoid", 200, 20, false);

	// }
}
