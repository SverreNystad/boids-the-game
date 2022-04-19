package boidsgame;

import java.util.ArrayList;
import java.util.Collection;

public class World {
	// needs to get the size of the World.
	private boolean wraparound;
	private int xLength; // Must get the info from javaFX
	private int yHeight; // Must get the info from javaFX
	
	private Collection<Boid> allInitBoids; // Must get the info from settings.json or javaFX

	private double mouseX; // The last known cursor coordinate.
    private double mouseY; // The last known cursor coordinate.
	
	public World(int xLength, int yHeight, Collection<Boid> allInitBoids){
		this.xLength = xLength;
		this.yHeight = yHeight;
		this.allInitBoids = allInitBoids;
		this.wraparound = false;
	}
	public World(int xLength, int yHeight, Collection<Boid> allInitBoids, Boolean wraparound){
		this.xLength = xLength;
		this.yHeight = yHeight;
		this.allInitBoids = allInitBoids;
		this.wraparound = wraparound;
	}

	public void validWorld(int xLength, int yHeight){
		// TODO: Shall throw exceptions if the world is faulty must get the size of the window somehow.
	}
	

	public int getxLength() {
		return xLength;
	}

	public int getyHeight() {
		return yHeight;
	}

	public Collection<Boid> getAllInitBoids() {
		return allInitBoids;
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
		this.allInitBoids = allInitBoids;
	}
	/**
	 * removeAllDeadBoids goes trough each boid in the world and removes all non living boids.
	 * This can be handy to reduce lag.
	 */
	public void removeAllDeadBoids(){
		for (Boid currentBoid : allInitBoids) {
			if (!currentBoid.isAlive()){
				allInitBoids.remove(currentBoid);
			}
		}
	}

	public static World initGame(int canvasLength, int canvasHeight, String gameMode, int startBoidsAmount, int startPoidProsent, Boolean wraparound ) {
		Collection<Boid> allInitBoids = new ArrayList<>();
		World gameWorld = new World(canvasLength, canvasHeight, allInitBoids, wraparound);
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
			if (!currentBoid.isAlive) continue; // if current boid is not alive it is no reason to move it futher.
			if (currentBoid instanceof PlayerBoid){
				/* Only needs to change the mouse coords if it is the PlayerBoid.
				   Let me use most of the methods given by boid but changes the internal fields to PlayerBoid. */
				((PlayerBoid) currentBoid).setMouseX(mouseX);
				((PlayerBoid) currentBoid).setMouseY(mouseY);
			}
			currentBoid.move();
			// System.out.println(currentBoid.getPosition().getPositionX() + " " + currentBoid.getPosition().getPositionY());
			// If boids go out of the world bounds and wraparound is legal then change the coordinates
			if (this.wraparound){
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
}
