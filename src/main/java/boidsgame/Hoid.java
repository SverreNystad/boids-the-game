package boidsgame;

import java.util.ArrayList;
import java.util.Collection;
/**
 * Hoids stands for Herd-oid object. It follows the classic Boids algorithm.
 */
public class Hoid extends Boid {
	private double cohesionCoefficient;
	private double seperationCoefficient;
	private double alignmentCoefficient;

	public Hoid(Vector position, Vector velocity, Vector acceleration, int maxVelocity, int maxAcceleration, int viewRangeRadius, boolean isAlive, World boidWorld, double cohesionCoefficient, double seperationCoefficient, double alignmentCoefficient){
		super(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld);
		try {
			vailedArgs(cohesionCoefficient, seperationCoefficient, alignmentCoefficient);
			this.cohesionCoefficient = cohesionCoefficient;
			this.seperationCoefficient = seperationCoefficient;
			this.alignmentCoefficient = alignmentCoefficient;

		} catch (IllegalArgumentException e) {
			this.cohesionCoefficient = 0d;
			this.seperationCoefficient = 0d;
			this.alignmentCoefficient = 0d;
		}
		
	}

	/**
	 * This method will throw IllegalArgumentException if any arguments are negativ.
	 * @param args can be a variable amount.
	 * @throws IllegalArgumentException 
	 */
	private static void vailedArgs(final double... args) throws IllegalArgumentException{
		for (double num : args) {
			if (num < 0) throw new IllegalArgumentException("No negativ arguments allowed");
		}
	}

	/**
	 * Checks if the boid is friendly to hoids. 
	 * @param boid
	 * @return true if either it is hoid or it is playerBoid that is hoid-oid. 
	 */
	@Override
	public boolean isFriendlyBoid(Boid boid){
		return (boid instanceof Hoid) || ((boid instanceof PlayerBoid) ? ((PlayerBoid) boid).getGameMode().equals("Hoid"): false);
	}

	/**
	 * cohesionVector finds the vector that point to the mass sentrum that is in seight from this boid.
	 * This makes Hoids try to get to the middel of the flock, where there is most protection from Poids.
	 * @param allCloseBoids This is the group of Boids this hoid can see.
	 * @return Vector to local mass sentrum.
	 */
	public Vector cohesionVector(Collection<Boid> allCloseBoids){
		// Size must be not null else DivisionByNullException
		if (allCloseBoids.size() != 0){
			int averegeXCord = (int) (allCloseBoids.stream().filter(boid -> isFriendlyBoid(boid)).mapToInt(boid -> boid.getPosition().getPositionX()).sum()) / allCloseBoids.size();
			int averegeYCord = (int) (allCloseBoids.stream().filter(boid -> isFriendlyBoid(boid)).mapToInt(boid -> boid.getPosition().getPositionY()).sum()) / allCloseBoids.size();
			return this.getPosition().distenceBetweenVector(new Vector(averegeXCord, averegeYCord));
		}
		else{
			// if there are no other boids in viewRange its current position is flock sentrum
			// return this.getPosition();
			return this.getPosition().distenceBetweenVector(this.getPosition());
		}
	}


	/**
	 * alignmentVector find the averege direction the boids are moving toward.
	 * It tries to steer the boid in the same direction as the local flockmates.
	 * @param allCloseBoids This is the group of Boids this hoid can see.
	 * @return the vector that steers the boid so it follows it flockmates.
	 */
	public Vector alignmentVector(Collection<Boid> allCloseBoids){
		Vector commenVector;
		if (allCloseBoids.size() != 0){
			int averegeXdirection = (int) (allCloseBoids.stream().filter(boid -> isFriendlyBoid(boid)).mapToInt(boid -> boid.getVelocity().getPositionX()).sum());
			int averegeYdirection = (int) (allCloseBoids.stream().filter(boid -> isFriendlyBoid(boid)).mapToInt(boid -> boid.getVelocity().getPositionY()).sum());
			commenVector =  new Vector(averegeXdirection, averegeYdirection);
		}
		else{
			// if there are no other boids in viewRange its current position is flock sentrum
			commenVector = this.getVelocity();
		}
		return commenVector.subtractionVector(this.getVelocity());
	}
	
	public Vector scareVector(Collection<Boid> allCloseBoids){
		// Add the distance from each Poid-oid.
		// Should use seperationVector but with only scary boids.
		// CAN CHANGE IT TO LAMBDA EXSPRESSION OR FUNCTIONAL
		Collection<Boid> unFriendlyBoids = new ArrayList<>();
		for (Boid boid : allCloseBoids) {
			if (!isFriendlyBoid(boid)){
				unFriendlyBoids.add(boid);
			}
		}
		this.setDistanseToFear(this.getViewRangeRadius());
		Vector resultVector = super.seperationVector(unFriendlyBoids);
		this.setDistanseToFear(20);
		return resultVector;
	}

	public double getCohesionCoefficient() {
		return cohesionCoefficient;
	}

	public double getSeperationCoefficient() {
		return seperationCoefficient;
	}

	public double getAlignmentCoefficient() {
		return alignmentCoefficient;
	}

	@Override
	public void move() {
		// Removes acceleration from last iteration
		this.setAcceleration(new Vector(0, 0));
		// Adds forces to acceleration
		this.acceleration.addition(cohesionVector(findAllBoidsInViewRange()).scalingNewVector(getCohesionCoefficient()));
		this.acceleration.addition(seperationVector(findAllBoidsInViewRange()).scalingNewVector(getSeperationCoefficient()));
		this.acceleration.addition(alignmentVector(findAllBoidsInViewRange()).scalingNewVector(getAlignmentCoefficient()));
		this.acceleration.addition(scareVector(findAllBoidsInViewRange()));
		this.acceleration.addition(super.wallScarVector());
		// Make certain it can not go faster then maxAcceleration
		this.limitAcceleration();
		// change speed depending on acceleration
		// Make certain it can not go faster then maxVelocity
		this.velocity.addition(this.acceleration);
		this.limitVelocity();
		// Move boid
		this.position.addition(this.velocity);	
	}
}
