package boidsgame;

import java.util.Collection;

public class World {
	// needs to get the size of the World.
	public int xLength; // Must get the info from javaFX
	public int yHeight; // Must get the info from javaFX
	public Collection<BoidsInterface> allInitBoids; // Must get the info from settings.json or javaFX
	
	public World(int xLength, int yHeight, Collection<BoidsInterface> allInitBoids){
		this.xLength = xLength;
		this.yHeight = yHeight;
		this.allInitBoids = allInitBoids;
	}

	public void validWorld(int xLength, int yHeight, windowSize){
		// TODO: Shall throw exceptions if the world is faulty must get the size of the window somhow.
	}
	

	public int getxLength() {
		return xLength;
	}
	public int getyHeight() {
		return yHeight;
	}

	public Collection<BoidsInterface> getAllInitBoids() {
		return allInitBoids;
	}

	public void removeAllDeadBoids(){
		for (BoidsInterface currentBoid : allInitBoids) {
			if (!currentBoid.isAlive()){
				allInitBoids.remove(currentBoid);
			}
		}
	}

}
