package boidsgame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;

public class GameController {
	@FXML private ImageView background;
	@FXML private Canvas worldCanvas;

	// Play Variables
	private World gameWorld;
	private List<String> settings;

	@FXML
	public void initialize() {
		try {
			settings = Filehandler.readFromSettingsfile();
		}
		catch (FileNotFoundException e){
			System.out.println("FileNotFoundException: " + e.getMessage());
		}
		System.out.println(settings);
		int canvasLength = (int) worldCanvas.getWidth();
		int canvasHeight = (int) worldCanvas.getHeight();
		gameWorld = World.initGame(canvasLength, canvasHeight, settings.get(4), (int) Integer.valueOf(settings.get(5)), (int) Integer.valueOf(settings.get(6)), (settings.get(7)).equals("on"));
		// background.toFront();
		worldCanvas.toFront();

		runGame();
	}

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
						// TODO: LAGRE HIGHSCORES OG SKIFTE TIL MAIN MENU
						// worldCanvas.toBack();
						// background.toBack();
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
		System.out.println("Draw");
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
			switch (currentBoid.getClass().getSimpleName()) {
				case "Hoid":
					currentColor = Color.GREEN;
					break;
				case "Poid":
					currentColor = Color.RED;
					break;
				case "PlayerBoid":
					currentColor = Color.WHITE;
					break;
				default:
					currentColor = Color.BLACK;
			}
			int radius = 5;
			// System.out.println(currentBoid.getClass().getSimpleName());
			gc.setFill(currentColor);
			gc.strokeOval(currentBoid.getPosition().getPositionX() - radius, currentBoid.getPosition().getPositionY() - radius, radius, radius);
			gc.fillOval(currentBoid.getPosition().getPositionX() - radius, currentBoid.getPosition().getPositionY() - radius, radius, radius); // does not do anything
			gc.closePath();
		}
	}
}
