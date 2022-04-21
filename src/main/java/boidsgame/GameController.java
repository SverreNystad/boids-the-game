package boidsgame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.animation.AnimationTimer;

public class GameController {
	private Stage stage;
	private Scene scene;
	// Main menu
	@FXML private Button playButton;
	@FXML private Button settingsButton;
	@FXML private Button quitButton;

	// Settings
	@FXML private Slider startBoidsAmountSlider;
	@FXML private Label startBoidsAmount;
	@FXML private Slider startPoidProsentSlider;
	@FXML private Label startPoidProsent;
	@FXML private RadioButton rbHoid;
	@FXML private RadioButton rbPoid;
	@FXML private ToggleButton wraparoundButton;

	@FXML private ImageView background;

	// Play
	@FXML private Canvas worldCanvas;

	// Settings Variables
	private String gameMode = "Hoid";
	private String wraparound = "on";
	private int startBoidsAmountSliderValue;
	private int startPoidProsentSliderValue;
	// Play Variables
	private World gameWorld;

	// Settings controller
	/**
	 * handleGamemodeSwitch is called when one click the button in settings. It
	 * checks what gameMode the player wants to play
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

	// Main menu controller
	@FXML
	private void switchToMainMenu(ActionEvent event) throws IOException {
		Filehandler.storeSettingsInFile(gameMode, startBoidsAmountSliderValue, startPoidProsentSliderValue, wraparound); // Saves settings
		switchToScene(event, "mainMenu.fxml");
	}

	@FXML
	private void switchToSettings(ActionEvent event) throws IOException {
		System.out.println("Load settings");
		switchToScene(event, "settings.fxml");
	}

	@FXML
	private void switchToPlay(ActionEvent event) throws IOException {
		// switchToScene(event, "play.fxml");
		List<String> settings = Filehandler.readFromSettingsfile();
		System.out.println(settings);
		int canvasLength = (int) worldCanvas.getWidth(); // TODO: shall get values from canvas when it works.
		int canvasHeight = (int) worldCanvas.getHeight(); // TODO: shall get values from canvas when it works.
		gameWorld = World.initGame(canvasLength, canvasHeight, settings.get(4), (int) Integer.valueOf(settings.get(5)), (int) Integer.valueOf(settings.get(6)), (settings.get(7)).equals("on"));
		background.toFront();
		worldCanvas.toFront();
		runGame();
	}

	/**
	 * A general switch to scene method. It takes in the filename and changes scene
	 * to current scene.
	 * @param event    ActionEvent fired by the FXML.
	 * @param filename is a string that represent the filename.
	 * @throws IOException
	 */
	private void switchToScene(ActionEvent event, String filename) throws IOException {
		// stage = (Stage)(settingsButton.getSource()).getScrene().getWindow();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(FXMLLoader.load(GameApp.class.getResource(filename)));
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Kills the Program
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void quitProgram(ActionEvent event) throws IOException {
		Platform.exit();
	}

	// GAME methods:
	// game controller

	@FXML
	private void handleMouseCoordinates(MouseEvent event) throws IOException {
		gameWorld.setMouseX(event.getX());
		gameWorld.setMouseY(event.getY());
		// System.out.println("x: " + event.getX() + " y: " + event.getY());
	}

	public void runGame() {
		new AnimationTimer() {
			private long lastUpdate = 0;

			@Override
			public void handle(long now) {
				// Finds the excecuton time and subtracts it from the sleep. This makes each frame take about 10 ms, witch makes 100 fps.
				if (now - lastUpdate >= 10_000_000) { // 10 000 000 ns = 10 ms
					drawBoidsOnCanvas();
					gameWorld.moveAllBoids();
					// System.out.println("Move");
					Boolean gameOver = gameWorld.isWordsPlayerAlive();
					if (!gameOver) {
						worldCanvas.toBack();
						background.toBack();
						stop();
					}
				}
				lastUpdate = now;
			}
		}.start();
	}

	/**
	 * Draws each individeual frame on the canvas. Each frame represent the current
	 * flora of Boids.
	 * It uses the positions of each boid that is in the gameWorld and paints it on
	 * the frame.
	 */
	public void drawBoidsOnCanvas() {
		// System.out.println("Draw");
		GraphicsContext gc = worldCanvas.getGraphicsContext2D();
		// Must clear the screen at the start of each update:
		gc.clearRect(0, 0, worldCanvas.getWidth(), worldCanvas.getHeight());

		for (Boid currentBoid : gameWorld.getAllInitBoids()) {
			// gc.beginPath();
			// // Image img = new Image(new File(currentBoid.getClass().getSimpleName() +
			// ".png").toURI().toString());
			// Image img = new Image(new File("birdIcon.png").toURI().toString());
			// gc.drawImage(img, currentBoid.getPosition().getPositionX(),
			// currentBoid.getPosition().getPositionY());
			// gc.closePath();

			// TEST With boids
			gc.beginPath();
			Color currentColor = new Color(0, 0, 0, 0);
			switch (currentBoid.getClass().getName()) {
				case "Hoid":
					currentColor = Color.GREEN;
					break;
				case "Poid":
					currentColor = Color.RED;
					break;
				case "PlayerBoid":
					currentColor = Color.BLACK;
					break;
			}
			int radius = 5;
			gc.setFill(currentColor);
			gc.strokeOval(currentBoid.getPosition().getPositionX() - radius, currentBoid.getPosition().getPositionY() - radius, radius, radius);
			gc.fillOval(currentBoid.getPosition().getPositionX() - radius, currentBoid.getPosition().getPositionY() - radius, radius, radius); // does not do anything
			gc.closePath();
		}
	}
}
