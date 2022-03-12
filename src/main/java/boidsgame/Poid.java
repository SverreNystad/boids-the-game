package boidsgame;
import java.util.Collection;

public class Poid extends Boid implements BoidsInterface{
	private int killRadius;
	private int killAmount;


	public Poid(){
		// # TODO: Fix contstuctor. Do validation. Should not be able to be out of World
		
		// super(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius)
		// this.killRadius = killRadius;
		// this.killAmount = killAmount;
		
	}

	public BoidsInterface findClosestBoid(Collection<BoidsInterface> allCloseBoids){
		// Loop through and find boid with closes distance.
		// must check its not another poid
		BoidsInterface closestBoid = null;
		Double shortestDistance = null;


		for (BoidsInterface currentBoid : allCloseBoids) {
			if(currentBoid.getClass().getName().equals("Poid")){ // could be fun if a larger poid could eate other poids
				continue;
			}
			if(shortestDistance >= (this.position.distenceBetweenVector(currentBoid.getPosition()).length())){
				closestBoid = currentBoid;
				shortestDistance = (this.position.distenceBetweenVector(currentBoid.getPosition()).length());
			}
		}
		return closestBoid;
	}
	// TODO: Make method to kill closest boids and increment killAmount
	public void killClosestBoid(BoidsInterface closestBoid){
		if (!closestBoid.equals(null)){
			closestBoid.setIsAlive(false);
			this.killAmount++;
		}
	}
	// TODO: Make a method to move towards the boid
	public Vector closestBoidVector(BoidsInterface closestboid){

		
	}
}
