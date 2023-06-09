package boidsgame.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import boidsgame.Filehandler;
import boidsgame.World;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;

public class GameController extends Controller{
	@FXML private ImageView background;
	@FXML private Canvas worldCanvas;
	@FXML private Label totalLifetime, TotalKillAmount;


	// Play Variables
	private World gameWorld;
	private List<String> settings;

	@FXML
	private void initialize() {
		try {
			settings = Filehandler.readFromSettingsfile();
		}
		catch (FileNotFoundException e){
			System.out.println("FileNotFoundException: " + e.getMessage());
		}
		gameWorld = World.initGame((int) worldCanvas.getWidth(), (int) worldCanvas.getHeight(), settings.get(12), (int) Integer.valueOf(settings.get(13)), (int) Integer.valueOf(settings.get(14)), (settings.get(15)).equals("on"), (int) Integer.valueOf(settings.get(16)), (int) Integer.valueOf(settings.get(17)), (double) Double.valueOf(settings.get(18)), (double) Double.valueOf(settings.get(19)), (int) Integer.valueOf(settings.get(20)), (double) Double.valueOf(settings.get(21)), (double) Double.valueOf(settings.get(22)), (double) Double.valueOf(settings.get(23)));
		worldCanvas.toFront();
		runGame();
	}

	@FXML
	private void handleMouseCoordinates(MouseEvent event) throws IOException {
		gameWorld.setMouseX(event.getX());
		gameWorld.setMouseY(event.getY());
		// System.out.println("x: " + event.getX() + " y: " + event.getY());
	}

	@FXML
	private void switchToMainMenu(ActionEvent event) throws IOException {
		Filehandler.storeHighscoresInFile(gameWorld.getWorldsPlayerboid());
		switchToScene(event, "mainMenu.fxml");
	}

	private void updateEndGameTextFilds(){
		TotalKillAmount.setText(String.valueOf(gameWorld.getWorldsPlayerboid().getKillScore()));
		totalLifetime.setText(String.valueOf(gameWorld.getWorldsPlayerboid().getLifeTime()));
	}

	/**
	 * The runGame method will start a animationTimer that updates every 10 ms. 
	 * Each update draws the frame and moves each boid according to its rules.
	 */
	private void runGame() {
		new AnimationTimer() {
			private long lastUpdate = 0;

			@Override
			public void handle(long now) {
				// Finds the excecuton time and subtracts it from the sleep. This makes each frame take about 10 ms, witch makes 100 fps.
				if (now - lastUpdate >= 10_000_000) { // 10 000 000 ns = 10 ms
					gameWorld.drawBoidsOnCanvas(worldCanvas);
					gameWorld.moveAllBoids();
					if (!gameWorld.getGameOn()) {
						System.out.println("Game Over");
						worldCanvas.toBack();
						background.toBack();
						stop();
						updateEndGameTextFilds();
					}
				}
				lastUpdate = now;
			}
		}.start();
	}
}
