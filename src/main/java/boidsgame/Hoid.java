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
	private double minDistanceToOtherBoids;


	public Hoid(Vector position, Vector velocity, Vector acceleration, int maxVelocity, int maxAcceleration, int viewRangeRadius, boolean isAlive, World boidWorld, double cohesionCoefficient, double seperationCoefficient, double alignmentCoefficient){
		// TODO: Fix contstuctor. Do validation. Should not be able to be out of World
		super(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld);
		this.cohesionCoefficient = cohesionCoefficient;
		this.seperationCoefficient = seperationCoefficient;
		this.alignmentCoefficient = alignmentCoefficient;
		this.minDistanceToOtherBoids = 10;
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

	// /**
	//  * When boids get to close the boid want to get away to keep a healhy distance. 
	//  * SperationVector gives the vector that goes away from all boids and tries to create greater distance with boids that are closer.
	//  * @param allCloseBoids This is the group of Boids this hoid can see.
	//  * @return gives a vector pointing away from boid.
	//  */
	// public Vector seperationVector(Collection<Boid> allCloseBoids){ 
	// 	Collection<Vector> allSeperationVectors = new ArrayList<>();
	// 	for (Boid currentBoid : allCloseBoids) {
	// 		Vector distanceVector = currentBoid.getPosition().distenceBetweenVector(this.getPosition());  // Allways zero. Does not change to (currentBoid.getPosition()).distenceBetweenVector(this.getPosition())
	// 		// System.out.println("x " + distanceVector.getPositionX() + " y " + distanceVector.getPositionY() + " len " + distanceVector.length()); // TODO REMOVE
	// 		// It is important that boids far away not have much impact but boids close should make the boid much more causus for collition // BUG Could become Zero: 1/distanceVector.length()
	// 		if (distanceVector.length() == 0) continue; 
	// 		allSeperationVectors.add(distanceVector.scalingNewVector((this.minDistanceToOtherBoids/distanceVector.length()))); 
	// 	}
	// 	Vector vectorsTogheter = new Vector(0, 0);
	// 	for (Vector currentVector : allSeperationVectors) {
	// 		vectorsTogheter.addition(currentVector);
	// 	}
	// 	// System.out.println(vectorsTogheter.getPositionX() + " " + vectorsTogheter.getPositionY());
	// 	return vectorsTogheter;//.scalingNewVector(scalar)
	// }

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
		// TODO: Must make the steerimgVector out of the averege direction: steeringVector = disired velocity - this.velocity
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
		return super.seperationVector(unFriendlyBoids);
	}

	@Override
	public void move() {
		// Removes acceleration from last iteration
		this.setAcceleration(new Vector(0, 0));
		// Adds forces to acceleration
		this.acceleration.addition(cohesionVector(findAllBoidsInViewRange()).scalingNewVector(cohesionCoefficient));
		this.acceleration.addition(seperationVector(findAllBoidsInViewRange()).scalingNewVector(seperationCoefficient));
		this.acceleration.addition(alignmentVector(findAllBoidsInViewRange()).scalingNewVector(alignmentCoefficient));
		this.acceleration.addition(scareVector(findAllBoidsInViewRange()));
		this.acceleration.addition(super.wallScarVector());
		// System.out.println(
		// 	"Cohesion: x: " + cohesionVector(findAllBoidsInViewRange()).scalingNewVector(cohesionCoefficient).getPositionX() + " y: " + cohesionVector(findAllBoidsInViewRange()).scalingNewVector(cohesionCoefficient).getPositionY() +
		// 	" Seperation: x: " + seperationVector(findAllBoidsInViewRange()).scalingNewVector(seperationCoefficient).getPositionX() + " y: " + seperationVector(findAllBoidsInViewRange()).scalingNewVector(seperationCoefficient).getPositionY() +
		// 	" Alignment: x: " + alignmentVector(findAllBoidsInViewRange()).scalingNewVector(alignmentCoefficient).getPositionX() + " y: " + alignmentVector(findAllBoidsInViewRange()).scalingNewVector(alignmentCoefficient).getPositionY() +
		// 	" Scare: x: " + scareVector(findAllBoidsInViewRange()).getPositionX() + " y: " + scareVector(findAllBoidsInViewRange()).getPositionY()
		// 	);
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
