package boidsgame;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Boid{
	protected Vector position;
	protected Vector velocity;
	protected Vector acceleration;
	protected int maxVelocity;
	protected int maxAcceleration;

	protected int viewRangeRadius;
	protected boolean isAlive;
	protected World boidWorld;

	private Collection<Boid> listOfBoidsInRange;

	public Boid(
		Vector position,
		Vector velocity,
		Vector acceleration,
		int maxVelocity,
		int maxAcceleration,
		int viewRangeRadius,
		boolean isAlive,
		World boidWorld
		){
		vailedArgs(maxVelocity,maxAcceleration,viewRangeRadius);
		this.position = position;
		this.velocity = velocity;
		this.maxVelocity = maxVelocity;
		this.acceleration = acceleration;
		this.maxAcceleration = maxAcceleration;
		this.viewRangeRadius = viewRangeRadius;
		this.isAlive = isAlive;
		this.boidWorld = boidWorld;
	}
	/**
	 * This method will throw IllegalArgumentException if any arguments are negativ.
	 * @param args can be a variable amount.
	 */
	private static void vailedArgs(final int... args) throws IllegalArgumentException{
		// TODO: MUST BE TESTED.
		for (int num : args) {
			if (num < 0) throw new IllegalArgumentException("No negativ arguments allowed");
		}
	}
	/**
	 * findAllBoidsInViewRange() Will make a list of all boids that is in the view range of this boid.
	 * @param allBoids All initialized boids in a list.
	 * @return list of all boids around this boid.
	 */
	public Collection<Boid> findAllBoidsInViewRange(){
		// Collection<Boid> listOfBoidsInRange = new Collection<Boid>();
		Collection<Boid> allBoids = this.boidWorld.getAllInitBoids();
		listOfBoidsInRange = new ArrayList<>();
		// listOfBoidsInRange.clear();

		for (Boid currentBoid : allBoids) {
			if (this.boidInViewRange(currentBoid) && this != currentBoid && currentBoid.isAlive){
				listOfBoidsInRange.add(currentBoid);
			}
		}
		return listOfBoidsInRange;
	}
	/**
	 * Finds out if a boid is in range of this boid. Will not find boids out of bouds of screen. 
	 * @param otherBoid Can be any type of boid.
	 * @return True if in range else false.
	 */
	public boolean boidInViewRange(Boid otherBoid){
		// Must find if boid is in ViewRange
		return 
		// If X pos of other boid in range of current boid
		((this.getPosition().getPositionX() - this.viewRangeRadius) <= otherBoid.getPosition().getPositionX() && (this.getPosition().getPositionX() + this.viewRangeRadius) >= otherBoid.getPosition().getPositionX()) 
		// If Y pos of other boid in range of current boid
		&& ((this.getPosition().getPositionY() - this.viewRangeRadius) <= otherBoid.getPosition().getPositionY() && (this.getPosition().getPositionY() + this.viewRangeRadius) >= otherBoid.getPosition().getPositionY());
	}
	/**
	 * If the boid goes out of the map and wraparound is allowed set the posision to the other side
	 */
	public void wraparoundCoordinates(){
		if (this.getPosition().getPositionX() < 0){
			this.setPosition(new Vector(this.boidWorld.getxLength(), this.getPosition().getPositionY()));
		}
		if (this.getPosition().getPositionX() > this.boidWorld.getxLength()){
			this.setPosition(new Vector(0, this.getPosition().getPositionY()));
		}
		if (this.getPosition().getPositionY() < 0){
			this.setPosition(new Vector(this.getPosition().getPositionX(), this.boidWorld.getyHeight()));
		}
		if (this.getPosition().getPositionY() > this.boidWorld.getyHeight()){
			this.setPosition(new Vector(this.getPosition().getPositionX(), 0));
		}
	}
	
	public Vector getPosition() {
		return position;
	}
	public void setPosition(Vector position) {
		this.position = position;
	}
	public Vector getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	public Vector getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
	public int getMaxVelocity() {
		return maxVelocity;
	}
	public void setMaxVelocity(int maxVelocity) {
		this.maxVelocity = maxVelocity;
	}
	public int getMaxAcceleration() {
		return maxAcceleration;
	}
	public void setMaxAcceleration(int maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}
	public int getViewRangeRadius() {
		return viewRangeRadius;
	}
	public void setViewRangeRadius(int viewRangeRadius) {
		this.viewRangeRadius = viewRangeRadius;
	}
	public boolean isAlive() {
		return this.isAlive;
	}
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public abstract void move();
	public static void main(String[] args) {
		// // Boid b1 = new Boid(100, 100, 0, 0, 0, 0, 0, 0);
		// // Boid b2 = new Boid(100, 100, 0, 0, 0, 0, 0, 0);
		// Vector v1 = new Vector(100, 100);
		// Vector v2 = new Vector(80, 90);
		// Vector v3 = new Vector(90, 80);
		// Vector v4 = new Vector(110, 110);



		// Boid b1 = new Boid(v1, v1, 0, v1, 0, 10);
		// Boid b2 = new Boid(v4, v1, 0, v1, 0, 10);
		// System.out.println(b1.viewRangeRadius);
		// if(b1.boidInViewRange(b2)){
		// 	System.out.println("Boid in range.");
		// }
	}
}