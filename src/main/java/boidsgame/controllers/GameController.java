package boidsgame.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import boidsgame.Boid;
import boidsgame.Filehandler;
import boidsgame.Vector;
import boidsgame.World;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;

public class GameController extends Controller{
	@FXML private ImageView background;
	@FXML private Canvas worldCanvas;
	@FXML private Label totalLifetime;
	@FXML private Label TotalKillAmount;


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
		System.out.println(settings); // TODO FIX INITGAME call
		gameWorld = World.initGame(
		(int) worldCanvas.getWidth(), (int) worldCanvas.getHeight(), 
		settings.get(4), (int) Integer.valueOf(settings.get(5)), (int) Integer.valueOf(settings.get(6)), (settings.get(7)).equals("on")
		, (int) Integer.valueOf(settings.get(8)), (int) Integer.valueOf(settings.get(9)), (double) Double.valueOf(settings.get(10)), (double) Double.valueOf(settings.get(11))
		, (int) Integer.valueOf(settings.get(12)), (double) Double.valueOf(settings.get(13)), (double) Double.valueOf(settings.get(14)), 0);
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

	@FXML
	private void switchToMainMenu(ActionEvent event) throws IOException {
		System.out.println("Game Over");
		Filehandler.storeHighscoresInFile(gameWorld.getWorldsPlayerboid());
		switchToScene(event, "mainMenu.fxml");
	}
	private void updateEndGameTextFilds(){
		TotalKillAmount.setText(String.valueOf(gameWorld.getWorldsPlayerboid().getKillScore()));
		totalLifetime.setText(String.valueOf(gameWorld.getWorldsPlayerboid().getLifeTime()));
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
					Boolean gameOver = gameWorld.isWorldsPlayerAlive(); // TODO MUST CHANGE gameOver so it includs no more hoids when player is poid
					if (!gameOver) {
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

	/**
	 * Draws each individeual frame on the canvas. Each frame represent the current
	 * flora of Boids.
	 * It uses the positions of each boid that is in the gameWorld and paints it on
	 * the frame.
	 */
	public void drawBoidsOnCanvas() {
		GraphicsContext gc = worldCanvas.getGraphicsContext2D();
		// Must clear the screen at the start of each update:
		gc.clearRect(0, 0, worldCanvas.getWidth(), worldCanvas.getHeight());

		for (Boid currentBoid : gameWorld.getAllInitBoids()) {
			if (!currentBoid.isAlive()) continue;
			// gc.beginPath();
			// Image img = new Image(new File(currentBoid.getClass().getSimpleName() +".png").toURI().toString());
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
					currentColor = Color.BLUE;
					break;
				default:
					currentColor = Color.BLACK;
			}
			int radius = 10;
			// Draws base circle
			gc.setFill(currentColor);
			gc.strokeOval(currentBoid.getPosition().getPositionX() - radius, currentBoid.getPosition().getPositionY() - radius, radius, radius);
			gc.fillOval(currentBoid.getPosition().getPositionX() - radius, currentBoid.getPosition().getPositionY() - radius, radius, radius);
			// Draws pointer
			// double[] horisontalX = {currentBoid.getPosition().getPositionX() - radius, currentBoid.getPosition().getPositionX() + radius , directionPoint.getPositionX()};
			Vector directionPoint = currentBoid.getPosition().additionNewVector(currentBoid.getVelocity().scalingVectorToSize(radius*2));
			double[] horisontalX = {currentBoid.getPosition().getPositionX() - radius, currentBoid.getPosition().getPositionX() + radius/4 , directionPoint.getPositionX()};
			double[] horisontalY = {currentBoid.getPosition().getPositionY() - radius/2, currentBoid.getPosition().getPositionY() - radius/2, directionPoint.getPositionY()};
			
			// double[] verticalX = {currentBoid.getPosition().getPositionX() - radius, currentBoid.getPosition().getPositionX() - radius, directionPoint.getPositionX()};
			// double[] verticalY = {currentBoid.getPosition().getPositionY() - radius, currentBoid.getPosition().getPositionY() + radius/2, directionPoint.getPositionY()};

			gc.strokePolygon(horisontalX, horisontalY, 3); // horisontal
			// gc.strokePolygon(verticalX, verticalY, 3); // vertical
			gc.fillPolygon(horisontalX, horisontalY, 3);
			// gc.fillPolygon(verticalX, verticalY, 3);
			gc.closePath();
		}
	}
}
