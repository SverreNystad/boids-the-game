package boidsgame;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class World {
	private boolean wraparound;
	private boolean worldsPlayerAlive = true;
	private int xLength;
	private int yHeight;
	
	private Collection<Boid> allInitBoids; // Must get the info from settings.json or javaFX

	private double mouseX; // The last known cursor coordinate.
    private double mouseY; // The last known cursor coordinate.
	
	public World(int xLength, int yHeight, Collection<Boid> allInitBoids){
		validWorld(xLength, yHeight);
		this.xLength = xLength;
		this.yHeight = yHeight;
		this.allInitBoids = allInitBoids;
		this.wraparound = false;
	}

	public World(int xLength, int yHeight, Collection<Boid> allInitBoids, Boolean wraparound){
		validWorld(xLength, yHeight);
		this.xLength = xLength;
		this.yHeight = yHeight;
		this.allInitBoids = allInitBoids;
		this.wraparound = wraparound;
	}

	/**
	 *  Shall throw exceptions if the world is faulty.
	 * @param xLength
	 * @param yHeight
	 * @throws IllegalArgumentException if a negative parameter is given.
	 */
	public void validWorld(int xLength, int yHeight) throws IllegalArgumentException {
		if (xLength < 0 || yHeight < 0) throw new IllegalArgumentException("This world cannot be. The length and height must be positiv");
	}
	
	/**
	 * The setAllInitBoids adds all the boids into the world. But a World cannot be lifeless. It must have at least one boid.
	 * Either a Playerboid, Poid, or Hoid.
	 * The function can be used in later iterations of the program to load a set of boid into a world, e.g to load a savegame.
	 * @param allInitBoids All boids that shall live in this World.
	 * @throws IllegalArgumentException Cannot be a lifeless World.
	 */
	public void setAllInitBoids(Collection<Boid> allInitBoids) throws IllegalArgumentException{
		if (allInitBoids.size() == 0) throw new IllegalArgumentException("No boid are initialization\n Initialization failed!");
		for (Boid currentBoid : allInitBoids) {
			currentBoid.setBoidWorld(this);
		}
		this.allInitBoids = allInitBoids;
	}

	/**
	 * This method makes a new World that is based by the canvas element. 
	 * It creates all the boids that the parameters startBoidsAmount calls for. 
	 * These Boids get a random velocity and starts off with zero acceleration.
	 * A portion of these will be made to Poids deppending on the startPoidProsent.
	 * It adds one PlayerBoid in the middel of the canvas.
	 * @param canvasLength this int value will correlate with the new World generated.
	 * @param canvasHeight this int value will correlate with the new World generated.
	 * @param gameMode Either "Poid" or "Hoid" for now. Will determine the player interactions with other boids.
	 * @param startBoidsAmount The amount of Boids genetated.
	 * @param startPoidProsent The amount of Boids being Poid.
	 * @param wraparound Shall the map be wraparound or shall it be a border.
	 * @param poidViewRange how far the poid can see.
	 * @param killRadius the distance to kill other boids
	 * @param poidSeperationCoefficient how strong the will to seperate is.
	 * @param attractionToHoidsCoefficient how strong the will to kill hoids is.
	 * @param hoidViewRange how far the hoid can see.
	 * @param cohesionCoefficient how strong the will to stay together is.
	 * @param alignmentCoefficient how strong the will to align is.
	 * @param hoidSeperationCoefficient how strong the will to seperate is.
	 * @return a new World that all boids (PlayerBoid, Hoids and Poids) lives in.
	 */
	public static World initGame(int canvasLength, int canvasHeight, String gameMode, int startBoidsAmount, int startPoidProsent, Boolean wraparound, int poidViewRange, int killRadius, double poidSeperationCoefficient, double attractionToHoidsCoefficient, int hoidViewRange, double cohesionCoefficient, double alignmentCoefficient, double hoidSeperationCoefficient) {
		Collection<Boid> allInitBoids = new ArrayList<>();
		World gameWorld = new World(canvasLength, canvasHeight, allInitBoids, wraparound);
		// Adds playerBoid.
		allInitBoids.add(new PlayerBoid(new Vector(canvasLength/2, canvasHeight/2), new Vector(0, 0), new Vector(0, 0), 10, 30, 10, true, gameWorld, gameMode));
		// Adds all Poids and Hoids in random locations and with a random speed
		int poidAmount = (int) Math.floor((startBoidsAmount) * startPoidProsent/100);
		// int hoidAmount = startBoidsAmount - poidAmount;
		System.out.println("Boids " + startBoidsAmount + " poids " + poidAmount + " hoids " + (startBoidsAmount - poidAmount));// TODO: REMOVE
		for (int i = 1; i < startBoidsAmount; i++){
			int currentPositionX = (int) Math.floor(Math.random() * canvasLength); 
			int currentPositionY = (int) Math.floor(Math.random() * canvasHeight); 
			int currentVelocityX = (int) Math.floor(Math.random() * 10 - 5);
			int currentVelocityY = (int) Math.floor(Math.random() * 10 - 5);
			// System.out.println("x: " + currentPositionX +" y: " + currentPositionY + " dx: " + currentVelocityX + " dy: " + currentVelocityY); // TODO: REMOVE
			if (i <= poidAmount){
				allInitBoids.add(new Poid(new Vector(currentPositionX, currentPositionY), new Vector(currentVelocityX, currentVelocityY), new Vector(0, 0), 7, 20, poidViewRange, true, gameWorld, killRadius, attractionToHoidsCoefficient, poidSeperationCoefficient));
			}
			else{
				allInitBoids.add(new Hoid(new Vector(currentPositionX, currentPositionY), new Vector(currentVelocityX, currentVelocityY), new Vector(0, 0), 5, 20, hoidViewRange, true, gameWorld, cohesionCoefficient, hoidSeperationCoefficient, alignmentCoefficient));
			}
		}
		gameWorld.setAllInitBoids(allInitBoids);
		System.out.println("Initialization successful");
		return gameWorld;
	}
	/**
	 * moveAllBoids as the name implies moves every living boid in the this World.
	 * If it detects a boid that is dead it just skips that interation and move on to the next boid.
	 * The use of Abstract class makes it possible to call the move method of the boid.
	 * It must change the mouse coordinates before moving the playerboid.
	 */
	public void moveAllBoids(){
		for (Boid currentBoid : this.getAllInitBoids()) {
			// System.out.println(currentBoid.getPosition().getPositionX() + " " + currentBoid.getPosition().getPositionY());
			if (currentBoid instanceof PlayerBoid){
				/* Only needs to change the mouse coords if it is the PlayerBoid.
				Let me use most of the methods given by boid but changes the internal fields to PlayerBoid. */
				((PlayerBoid) currentBoid).setMouseX(mouseX);
				((PlayerBoid) currentBoid).setMouseY(mouseY);
				this.setWorldsPlayerAlive(currentBoid.isAlive);
			}
			if (!currentBoid.isAlive()) continue; // if current boid is not alive it is no reason to move it futher.
			currentBoid.move();
			// System.out.println(currentBoid.getPosition().getPositionX() + " " + currentBoid.getPosition().getPositionY());
			// If boids go out of the world bounds and wraparound is legal then change the coordinates
			if (this.getWraparound()){
				currentBoid.wraparoundCoordinates();
			}
		}
	}
	/**
	 * Changes the coordinates of the cursor, if the coordinates is not in the world then dont update.
	 * @param newMouseX The new coordinate of the cursor.
	 */
	public void setMouseX(double newMouseX) {
		if (newMouseX < 0 && newMouseX > this.getxLength()) return;
		this.mouseX = newMouseX;
	}
	
	public int getxLength() {
		return xLength;
	}

	public int getyHeight() {
		return yHeight;
	}

	public PlayerBoid getWorldsPlayerboid(){
		for (Boid currentBoid : allInitBoids) {
			if (currentBoid instanceof PlayerBoid){
				return (PlayerBoid) currentBoid;
			}
		}
		return null;
	}
	public int calculateAmountOfHoidsLeftAlive(){
		int amountLeftAlive = 0;
		for (Boid currentBoid : allInitBoids) {
			if (currentBoid instanceof Hoid && currentBoid.isAlive()){
				amountLeftAlive++;
			}
		}
		return amountLeftAlive;
	}

	public boolean isWorldsPlayerAlive() {
		return worldsPlayerAlive;
	}
	public void setWorldsPlayerAlive(boolean worldsPlayerAlive) {
		this.worldsPlayerAlive = worldsPlayerAlive;
	}
	public Collection<Boid> getAllInitBoids() {
		return allInitBoids;
	}

	public double getMouseX() {
		return mouseX;
	}
	/**
	 * Changes the coordinates of the cursor, if the coordinates is not in the world then dont update.
	 * @param newMouseY The new coordinate of the cursor.
	 */
	public void setMouseY(double newMouseY) {
		if (newMouseY < 0 && newMouseY > this.getyHeight()) return;
		this.mouseY = newMouseY;
	}

	public double getMouseY() {
		return mouseY;
	}
	public boolean getWraparound(){
		return this.wraparound;
	}

	/**
	 * Draws each individeual frame on the canvas. Each frame represent the current
	 * flora of Boids.
	 * It uses the positions of each boid that is in the gameWorld and paints it on
	 * the frame.
	 */
	public void drawBoidsOnCanvas(Canvas worldCanvas) {
		GraphicsContext gc = worldCanvas.getGraphicsContext2D();
		// Must clear the screen at the start of each update:
		gc.clearRect(0, 0, worldCanvas.getWidth(), worldCanvas.getHeight());

		for (Boid currentBoid : this.getAllInitBoids()) {
			if (!currentBoid.isAlive()) continue;
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
			double[] horisontalY = {currentBoid.getPosition().getPositionY() - radius/2, currentBoid.getPosition().getPositionY() - radius/2, directionPoint.getPositionY() - radius/2};
			
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
