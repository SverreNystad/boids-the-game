package boidsgame;

import java.util.Collection;
// Herd-oid object
public class Hoid extends Boid implements BoidsInterface {
	private Collection<Vector> allSeperationVectors;


	private int cohesionCoefficient;
	private int seperationCoefficient;
	private int alignmentCoefficient;


	public Hoid(Vector position, Vector velocity, Vector acceleration, int maxVelocity, int maxAcceleration, int viewRangeRadius, int cohesionCoefficient, int seperationCoefficient, int alignmentCoefficient){
		// TODO: Fix contstuctor. Do validation. Should not be able to be out of World
		super(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius);
		this.cohesionCoefficient = cohesionCoefficient;
		this.seperationCoefficient = seperationCoefficient;
		this.alignmentCoefficient = alignmentCoefficient;
	}



	public Vector cohesionVector(Collection<BoidsInterface> allCloseBoids){
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
	public static boolean isFriendlyBoid(BoidsInterface boid){
		return (boid.getClass().getName().equals("Hoid")) || (boid.getClass().getName().equals("PlayerBoid")); // Must
	}


	public Vector seperationVector(Collection<BoidsInterface> allCloseBoids){
		// TODO: seperationVector MUST BE TESTED

		// Collection<BoidsInterface> allCloseBoids = this.findAllBoidsInViewRange(); // må legge til listen med alle boids
		// Must find vector between this and other boids. 
		
		for (BoidsInterface currentBoid : allCloseBoids) {
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

	public Vector alignmentVector(Collection<BoidsInterface> allCloseBoids){
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







}
