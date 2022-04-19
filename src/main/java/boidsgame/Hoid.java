package boidsgame;

import java.util.ArrayList;
import java.util.Collection;
// Herd-oid object
public class Hoid extends Boid {
	private Collection<Vector> allSeperationVectors = new ArrayList<>();
	private int cohesionCoefficient;
	private int seperationCoefficient;
	private int alignmentCoefficient;


	public Hoid(Vector position, Vector velocity, Vector acceleration, int maxVelocity, int maxAcceleration, int viewRangeRadius, boolean isAlive, World boidWorld, int cohesionCoefficient, int seperationCoefficient, int alignmentCoefficient){
		// TODO: Fix contstuctor. Do validation. Should not be able to be out of World
		super(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld);
		this.cohesionCoefficient = cohesionCoefficient;
		this.seperationCoefficient = seperationCoefficient;
		this.alignmentCoefficient = alignmentCoefficient;
	}

	/**
	 * 
	 * @param allCloseBoids
	 * @return
	 */
	public Vector cohesionVector(Collection<Boid> allCloseBoids){
		// TODO: cohesionVector MUST BE TESTED

		// Size must be not null else DivisionByNullException. Could use try catch and catch it but it is bad pracsis.
		if (allCloseBoids.size() != 0){
			int averegeXCord = (int) (allCloseBoids.stream().filter(boid -> isFriendlyBoid(boid)).mapToInt(boid -> boid.getPosition().getPositionX()).sum()) / allCloseBoids.size();
			int averegeYCord = (int) (allCloseBoids.stream().filter(boid -> isFriendlyBoid(boid)).mapToInt(boid -> boid.getPosition().getPositionY()).sum()) / allCloseBoids.size();
			return new Vector(averegeXCord, averegeYCord);
		}
		else{
			// if there are no other boids in viewRange its current position is flock sentrum
			return this.position;
		}
	}
	public static boolean isFriendlyBoid(Boid boid){
		// return (boid.getClass().getName().equals("Hoid")) || (boid.getClass().getName().equals("PlayerBoid")); // Must
		return (boid instanceof Hoid) || (boid instanceof PlayerBoid);

	}


	public Vector seperationVector(Collection<Boid> allCloseBoids){
		// TODO: seperationVector MUST BE TESTED

		// Collection<Boid> allCloseBoids = this.findAllBoidsInViewRange(); // må legge til listen med alle boids
		// Must find vector between this and other boids. 
		
		for (Boid currentBoid : allCloseBoids) {
			Vector distanceVector = (this.position).distenceBetweenVector(currentBoid.getPosition()); 
			// It is important that boids far away not have much impact but boids close should make the boid much more causus for collition // BUG Could become Zero: 1/distanceVector.length()
			allSeperationVectors.add(distanceVector.scalingNewVector((1/distanceVector.length()))); 
		}
		Vector vectorsTogheter = new Vector(0, 0);
		for (Vector currentVector : allSeperationVectors) {
			vectorsTogheter.addition(currentVector);
		}
		allSeperationVectors.clear(); // unsurten if i must clear list. list gets decleared in method.
		return vectorsTogheter;//.scalingNewVector(scalar)
	}

	public Vector alignmentVector(Collection<Boid> allCloseBoids){
		// TODO: Must find the averege direction the boids are moving toward.
		// TODO: alignmentVector MUST BE TESTED
		Vector commenVector;
		if (allCloseBoids.size() != 0){
			int averegeXdirection = (int) (allCloseBoids.stream().filter(boid -> isFriendlyBoid(boid)).mapToInt(boid -> boid.getVelocity().getPositionX()).sum());
			int averegeYdirection = (int) (allCloseBoids.stream().filter(boid -> isFriendlyBoid(boid)).mapToInt(boid -> boid.getVelocity().getPositionY()).sum());
			commenVector =  new Vector(averegeXdirection, averegeYdirection);
		}
		else{
			// if there are no other boids in viewRange its current position is flock sentrum
			commenVector = this.velocity;
		}
		// TODO: Must make the steerimgVector out of the averege direction: steeringVector = disired velocity - this.velocity
		return commenVector.subtractionVector(this.velocity);
	}
	
	// TODO: Must make function to run from unfriendly boids.
	@Override
	public void move() {
		// Removes acceleration from last iteration
		this.setAcceleration(new Vector(0, 0));
		// Adds forces to acceleration
		this.acceleration.addition(cohesionVector(findAllBoidsInViewRange()).scalingNewVector(cohesionCoefficient));
		this.acceleration.addition(seperationVector(findAllBoidsInViewRange()).scalingNewVector(seperationCoefficient));
		this.acceleration.addition(alignmentVector(findAllBoidsInViewRange()).scalingNewVector(alignmentCoefficient));
		// change speed depending on acceleration
		// Make certain it can not go faster then maxVelocity
		this.velocity.addition(this.acceleration);
		if (this.velocity.length() > this.getMaxVelocity()){
			this.velocity = this.velocity.scalingVectorToSize(this.getMaxVelocity());
		}
		// Move boid
		this.position.addition(this.velocity);	
	}
}
