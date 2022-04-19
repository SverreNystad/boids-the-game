package boidsgame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.Node;



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

	// Play
	@FXML private Canvas worldCanvas;

	// Variables
	private String gameMode = "Hoid";
	private String wraparound = "on";
	private int startBoidsAmountSliderValue;
	private int startPoidProsentSliderValue;
	private Collection<Boid> allInitBoids = new ArrayList<>();
	private World gameWorld;

	private double mouseX;
    private double mouseY;

	/**
	 * handleGamemodeSwitch is called when one click the button in settings. It checks what gameMode the player wants to play
	 * 
	 */
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
	public void handleWraparound(ActionEvent event) throws IOException{
		wraparound = (wraparoundButton.isSelected()) ? "off" : "on";
	}

	// NOTE: Maight only have one function and buttenName be filename except filtype
	public void switchToMainMenu(ActionEvent event) throws IOException{
		Filehandler.storeSettingsInFile(gameMode, startBoidsAmountSliderValue, startPoidProsentSliderValue, wraparound); // Saves settings
		switchToScene(event, "mainMenu.fxml");
	}
	public void switchToSettings(ActionEvent event) throws IOException{
		System.out.println("Load settings");
		switchToScene(event, "settings.fxml");
	}
	public void switchToPlay(ActionEvent event) throws IOException{
		switchToScene(event, "play.fxml");
		List<String> settings = Filehandler.readFromSettingsfile();
		System.out.println(settings);
		initGame(settings.get(4), (int) Integer.valueOf(settings.get(5)),(int) Integer.valueOf(settings.get(6)), (settings.get(7)).equals("on"));
		// gameWorld = initGame(settings.get(4), (int) Integer.valueOf(settings.get(5)),(int) Integer.valueOf(settings.get(6)), (settings.get(7)).equals("on"));
		// TEST
		// System.out.println(gameWorld.getAllInitBoids());;
		
		runGame();
	}
	private void switchToScene(ActionEvent event, String filename) throws IOException{
		// stage = (Stage)(settingsButton.getSource()).getScrene().getWindow();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(FXMLLoader.load(GameApp.class.getResource(filename)));
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Kills the Program
	 * @param event
	 * @throws IOException
	 */
	public void quitProgram(ActionEvent event) throws IOException{
		Platform.exit();
	}

	// GAME methods:
	public void initGame(String gameMode, int startBoidsAmount, int startPoidProsent, Boolean wraparound ) {
		int canvasLength = 1280;
		int canvasHeight = 700;
		gameWorld = new World(canvasLength, canvasHeight, allInitBoids);

		// Adds playerBoid.
		allInitBoids.add(new PlayerBoid(new Vector(canvasLength/2, canvasHeight/2), new Vector(0, 0), new Vector(0, 0), 0,0,0, true, gameWorld, gameMode));
		// Adds all Poids and Hoids in random locations and with a random speed
		int poidAmount = (int) Math.floor((startBoidsAmount) * startPoidProsent/100);
		int hoidAmount = startBoidsAmount - poidAmount;
		
		for (int i = 1; i < startBoidsAmount; i++){
			int currentPositionX = (int) Math.floor(Math.random() * canvasLength);
			int currentPositionY = (int) Math.floor(Math.random() * canvasHeight);
			int currentVelocityX = (int) Math.floor(Math.random() * 10 - 5);
			int currentVelocityY = (int) Math.floor(Math.random() * 10 - 5);
			if (i < poidAmount){
				allInitBoids.add(new Poid(new Vector(currentPositionX, currentPositionY), new Vector(currentVelocityX, currentVelocityY), new Vector(0, 0), 20, 20, 50, true, gameWorld, 10, 10));
			}
			else{
				allInitBoids.add(new Hoid(new Vector(currentPositionX, currentPositionY), new Vector(currentVelocityX, currentVelocityY), new Vector(0, 0), 20, 20, 50, true, gameWorld, 10, 10, 10));
			}
		}
		gameWorld.setAllInitBoids(allInitBoids);
		System.out.println("Initialization successful");
	}
	
	public void handleMouseCoordinates(MouseEvent event) throws IOException{
		mouseX = event.getX();
		mouseY = event.getY();
		// System.out.println("x: " + mouseX + " y: " + mouseY);
	}
	public void runGame() {

		boolean gameOver = false;
		// boolean gameOver = PlayerBoid; Should be the status of isalive of playerboid
		
        /* … The code being measured starts … */
		while(!gameOver){
			long startTime = System.nanoTime();
			drawBoidsOnCanvas();
			moveAllBoids();
			try {
				Thread.sleep(17);
			}
			catch (InterruptedException e){}
			System.out.println("Move");
			
			
			
			/* … The code being measured ends … */
			
			long endTime = System.nanoTime();
			
			long timeElapsed = endTime - startTime;
			
			// System.out.println("Execution time in nanoseconds: " + timeElapsed);
			System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		}
	}
	public void drawBoidsOnCanvas(){
		// TODO FIND A WAY TO DRAW ON SCREEN.
		// System.out.println(worldCanvas);
		System.out.println("Draw");

		final GraphicsContext gc = worldCanvas.getGraphicsContext2D();
		
		// Must clear the screen at the start of each update:
		gc.clearRect(0, 0, worldCanvas.getWidth(), worldCanvas.getHeight());

		// for (Boid currentBoid : allInitBoids) {
			// gc.beginPath();			
			// // Image img = new Image(new File(currentBoid.getClass().getSimpleName() + ".png").toURI().toString());
			// Image img = new Image(new File("birdIcon.png").toURI().toString());
			// gc.drawImage(img, currentBoid.getPosition().getPositionX(), currentBoid.getPosition().getPositionY());
			// gc.closePath();
			
			// TEST
			// gc.beginPath();	
			// gc.setFill(Color.BLACK);	
			// gc.strokeRect(currentBoid.getPosition().getPositionX(), currentBoid.getPosition().getPositionX(), 30, 30);	
			// gc.fillRect(currentBoid.getPosition().getPositionX(), currentBoid.getPosition().getPositionX(), 30, 30);

			// gc.closePath();
		// }
		// gc.beginPath();
			// gc.	
			gc.setFill(Color.BLACK);	
			gc.strokeRect(300, 200, 300, 300);	
			gc.fillRect(300, 200, 300, 300);

			// gc.closePath();
	}
	public void moveAllBoids(){
		for (Boid currentBoid : allInitBoids) {
			// System.out.println(currentBoid.getPosition().getPositionX() + " " + currentBoid.getPosition().getPositionY());
			if (!currentBoid.isAlive) continue; // if current boid is not alive it is no reason to move it futher.
			if (currentBoid instanceof PlayerBoid){
				// Only needs to change the mouse coords if it is the PlayerBoid.
				((PlayerBoid) currentBoid).setMouseX(mouseX);
				((PlayerBoid) currentBoid).setMouseY(mouseY);
				// Let me use most of the methods given by boid but changes the internal fields to PlayerBoid.
                currentBoid.move();
                continue;
            }
			currentBoid.move();
			// System.out.println(currentBoid.getPosition().getPositionX() + " " + currentBoid.getPosition().getPositionY());

		}
	}
	// public static void main(String[] args) {
	// 	GameController test = new GameController();
	// 	test.initGame("Hoid", 200, 20, false);

	// }
}
