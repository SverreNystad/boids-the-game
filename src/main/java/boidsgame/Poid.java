package boidsgame;
import java.util.Collection;
public class Poid extends Boid{
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
	/**
	 * findClosestBoid will find the closest boid that this boid can see in findAllBoidsInViewRange(). It loops over the list and compare each boids distance. If it is not a target it will skip its current iteration. 
	 * @return The closest living non Poid-oid Boid
	 */
	private Boid findClosestBoid(){
		// Loop through and find boid with closes distance.
		Collection<Boid> allCloseBoids = this.findAllBoidsInViewRange();
		Boid closestBoid = null;
		Double shortestDistance = null;

		for (Boid currentBoid : allCloseBoids) {
			// dont look after other Poids or dead boids or Playerboid playing Poid.
			if(currentBoid instanceof Poid || !currentBoid.isAlive || ((currentBoid instanceof PlayerBoid) && ((PlayerBoid) currentBoid).getGameMode().equals("Hoid")) ){ // could be fun if a larger poid could eate other poids
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
	/**
	 * closestBoidVector(Boid closestBoid) Gives the vector from this boid to the other boid. It is used to change the acceleration.
	 * @param closestBoid
	 * @return Vector distance between boids.
	 */
	private Vector closestBoidVector(Boid closestBoid) {
		return this.getPosition().distenceBetweenVector(closestBoid.getPosition());
		
	}
	/**
	 * method to kill closest boids and increment killAmount
	 * @param closestBoid
	 */
	private void killClosestBoid(Boid closestBoid){
		if (closestBoid != null){
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
