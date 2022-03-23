package boidsgame;
import java.util.Collection;
//Edvard var her :)
public class Poid extends Boid implements BoidsInterface {
	private int killRadius;
	private int killAmount;
	private int movementCoefficient;


	public Poid(Vector position, Vector velocity, Vector acceleration, int maxVelocity, int maxAcceleration, int viewRangeRadius, boolean isAlive, World myWorld , int killRadius, int movementCoefficient){
		// # TODO: Fix contstuctor. Do validation. Should not be able to be out of World
		super(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld);
		this.killRadius = killRadius;
		this.killAmount = 0;
		this.movementCoefficient = movementCoefficient;
		
	}

	public BoidsInterface findClosestBoid(){
		// Loop through and find boid with closes distance.
		// must check its not another poid
		Collection<BoidsInterface> allCloseBoids = this.findAllBoidsInViewRange();
		BoidsInterface closestBoid = null;
		Double shortestDistance = null;


		for (BoidsInterface currentBoid : allCloseBoids) {
			if(currentBoid.getClass().getName().equals("Poid")){ // could be fun if a larger poid could eate other poids
				continue;
			}
			if (shortestDistance == null){
				closestBoid = currentBoid;
				shortestDistance = (this.position.distenceBetweenVector(currentBoid.getPosition()).length());
			}
			if(shortestDistance >= (this.position.distenceBetweenVector(currentBoid.getPosition()).length())){
				closestBoid = currentBoid;
				shortestDistance = (this.position.distenceBetweenVector(currentBoid.getPosition()).length());
			}
		}
		return closestBoid;
	}
	public Vector closestBoidVector(BoidsInterface closestBoid) {
		return this.getPosition().distenceBetweenVector(closestBoid.getPosition());
		
	}
	/**
	 * method to kill closest boids and increment killAmount
	 * @param closestBoid
	 */
	public void killClosestBoid(BoidsInterface closestBoid){
		if (!closestBoid.equals(null)){
			if (this.position.distenceBetweenVector(closestBoid.getPosition()).length() <= killRadius){
				closestBoid.setIsAlive(false);
				this.killAmount++;
			}
		}
	}

	public void move() {
		// resets acceleration
		this.setAcceleration(new Vector(0, 0));
		Vector forces = closestBoidVector(findClosestBoid()).scalingNewVector(this.movementCoefficient);
		this.acceleration.addition(forces);
		this.velocity.addition(this.acceleration);
		this.position.addition(this.velocity);
		// at the end of movment kill closest bird
		this.killClosestBoid(findClosestBoid());
	}
}
