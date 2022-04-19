package boidsgame;

import java.util.Collection;

public class World {
	// needs to get the size of the World.
	public int xLength; // Must get the info from javaFX
	public int yHeight; // Must get the info from javaFX
	public Collection<Boid> allInitBoids; // Must get the info from settings.json or javaFX
	
	public World(int xLength, int yHeight, Collection<Boid> allInitBoids){
		this.xLength = xLength;
		this.yHeight = yHeight;
		this.allInitBoids = allInitBoids;
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
	public void setAllInitBoids(Collection<Boid> allInitBoids) throws IllegalArgumentException{
		if (allInitBoids.size() == 0) throw new IllegalArgumentException("No boid are initialization\n Initialization failed!");
		this.allInitBoids = allInitBoids;
	}

	public void removeAllDeadBoids(){
		for (Boid currentBoid : allInitBoids) {
			if (!currentBoid.isAlive()){
				allInitBoids.remove(currentBoid);
			}
		}
	}

	// public World initGame(String gameMode, int startBoidsAmount, int startPoidProsent, Boolean wraparound ) {
	// 	int canvasLength = 1280;
	// 	int canvasHeight = 700;
	// 	World gameWorld = new World(canvasLength, canvasHeight, allInitBoids);

	// 	// Adds playerBoid.
	// 	allInitBoids.add(new PlayerBoid(new Vector(canvasLength/2, canvasHeight/2), new Vector(0, 0), new Vector(0, 0), 0,0,0, true, gameWorld, gameMode));
	// 	// Adds all Poids and Hoids in random locations and with a random speed
	// 	int poidAmount = (int) Math.floor((startBoidsAmount) * startPoidProsent/100);
	// 	int hoidAmount = startBoidsAmount - poidAmount;
		
	// 	for (int i = 1; i < startBoidsAmount; i++){
	// 		int currentPositionX = (int) Math.floor(Math.random() * canvasLength);
	// 		int currentPositionY = (int) Math.floor(Math.random() * canvasHeight);
	// 		int currentVelocityX = (int) Math.floor(Math.random() * 10 - 5);
	// 		int currentVelocityY = (int) Math.floor(Math.random() * 10 - 5);
	// 		if (i < poidAmount){
	// 			allInitBoids.add(new Poid(new Vector(currentPositionX, currentPositionY), new Vector(currentVelocityX, currentVelocityY), new Vector(0, 0), 20, 20, 50, true, gameWorld, 10, 10));
	// 		}
	// 		else{
	// 			allInitBoids.add(new Hoid(new Vector(currentPositionX, currentPositionY), new Vector(currentVelocityX, currentVelocityY), new Vector(0, 0), 20, 20, 50, true, gameWorld, 10, 10, 10));
	// 		}
	// 	}
	// 	gameWorld.setAllInitBoids(allInitBoids);
	// 	System.out.println("Initialization successful");
	// 	return gameWorld;
	// }

}
