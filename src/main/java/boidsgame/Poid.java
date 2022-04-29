package boidsgame;
import java.util.Collection;
/**
 * The poid class is meant to represent a Predetorial boid
 */
public class Poid extends Boid{
	private int killAmount;
	private double killRadius;
	private double attractionToHoidsCoefficient;
	private double seperationCoefficient;

	public Poid(Vector position, Vector velocity, Vector acceleration, int maxVelocity, int maxAcceleration, int viewRangeRadius, boolean isAlive, World myWorld , int killRadius, double attractionToHoidsCoefficient, double seperationCoefficient){
		super(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld);
		this.killAmount = 0;
		try{
			validePoidArgs(killRadius, attractionToHoidsCoefficient, seperationCoefficient);
			this.killRadius = killRadius;
			this.attractionToHoidsCoefficient = attractionToHoidsCoefficient;
			this.seperationCoefficient = seperationCoefficient;
		}
		catch (IllegalArgumentException e){
			this.killRadius = 0;
			this.attractionToHoidsCoefficient = 0;
			this.seperationCoefficient = 0;
		}
	}
	/**
	 * No negativ parameters allowed
	 * @param args any amount of doubles
	 * @throws IllegalArgumentException if an arg is negativ throw
	 */
	private void validePoidArgs(final double... args) throws IllegalArgumentException {
		for (double arg : args) {
			if (arg < 0) throw new IllegalArgumentException("No negativ arguments allowed");
		}
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
			if(currentBoid == this || this.isFriendlyBoid(currentBoid)){
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
		return (closestBoid == null) ? new Vector(0, 0) : this.getPosition().distenceBetweenVector(closestBoid.getPosition());
		
	}
	/**
	 * Method to kill closest boids and increment killAmount
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
	public int getKillAmount(){
		return this.killAmount;
	}
	public double getKillRadius() {
		return this.killRadius;

	}
	public double getAttractionToHoidsCoefficient() {
		return this.attractionToHoidsCoefficient;
	}
	public double getSeperationCoefficient() {
		return this.seperationCoefficient;
	}

	public void move() {
		// resets acceleration
		this.setAcceleration(new Vector(0, 0));
		// Adds forces to acceleration
		this.acceleration.addition(closestBoidVector(findClosestBoid()).scalingNewVector(this.getAttractionToHoidsCoefficient()));
		this.acceleration.addition(seperationVector(findAllBoidsInViewRange()).scalingNewVector(this.getSeperationCoefficient()));
		this.acceleration.addition(super.wallScarVector());

		// Make certain it can not go faster then maxAcceleration
		this.limitAcceleration();
		this.velocity.addition(this.getAcceleration());
		// Make certain it can not go faster then maxVelocity
		this.limitVelocity();

		this.position.addition(this.getVelocity());
		// at the end of movment kill closest bird
		this.killClosestBoid(findClosestBoid());
	}
	/**
	 * Could be fun if a larger poid could eat other poids
	 */
	@Override
	public boolean isFriendlyBoid(Boid boid) {
		return (boid instanceof Poid) || ((boid instanceof PlayerBoid) ? ((PlayerBoid) boid).getGameMode().equals("Poid"): false);
	}
}
