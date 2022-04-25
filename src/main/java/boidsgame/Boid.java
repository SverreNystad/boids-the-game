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
	protected int distanseToFear;
	protected boolean isAlive;
	protected World boidWorld;

	private Collection<Boid> listOfBoidsInRange;

	/**
	 * Contructor for the abstract class Boid. The children classes 
	 * @param position 
	 * @param velocity 
	 * @param acceleration 
	 * @param maxVelocity the max for velocity.
	 * @param maxAcceleration the max for acceleration.
	 * @param viewRangeRadius how far it can see
	 * @param isAlive the life status of the boid. If it is dead it not get to move.
	 * @param boidWorld The world that the boid is in. It can only interact with boids in the same world.
	 */
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
		this.distanseToFear = 20;
	}
	/**
	 * This method will throw IllegalArgumentException if any arguments are negativ.
	 * @param args can be a variable amount.
	 */
	private static void vailedArgs(final int... args) throws IllegalArgumentException{
		for (int num : args) {
			if (num < 0) throw new IllegalArgumentException("No negativ arguments allowed");
		}
	}
	/**
	 * <b>findAllBoidsInViewRange()</b> Will make a list of all boids that is in the view range of this boid.
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
		// return 
		// // If X pos of other boid in range of current boid
		// ((this.getPosition().getPositionX() - this.viewRangeRadius) <= otherBoid.getPosition().getPositionX() && (this.getPosition().getPositionX() + this.viewRangeRadius) >= otherBoid.getPosition().getPositionX()) 
		// // If Y pos of other boid in range of current boid
		// && ((this.getPosition().getPositionY() - this.viewRangeRadius) <= otherBoid.getPosition().getPositionY() && (this.getPosition().getPositionY() + this.viewRangeRadius) >= otherBoid.getPosition().getPositionY());
		return this.getPosition().distenceBetweenVector(otherBoid.getPosition()).length() <= getViewRangeRadius();
	}
	/**
	 * limitVelocity makes the Velocity not go over the maximum.
	 */
	public void limitVelocity(){
		if (this.getVelocity().length() > this.getMaxVelocity()){
			this.setVelocity(this.velocity.scalingVectorToSize(this.getMaxVelocity()));
		}
	}
	/**
	 * limitAcceleration makes the Acceleration not go over the maximum.
	 */
	public void limitAcceleration(){
		if (this.getAcceleration().length() > this.getMaxAcceleration()){
			this.setAcceleration(this.getAcceleration().scalingVectorToSize(this.getMaxAcceleration()));
		}
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

	/**
	 * If the world does not wraparound then the walls shall be scary and keep the boids at bay.
	 * @return A vector that points away from nearest wall.
	 */
	public Vector wallScarVector(){ 
		Vector resultVector = new Vector(0, 0);
		int boarderDistance = 50;
		int turnfactor = 45;
		if (!this.boidWorld.getWraparound()){
			if (this.getPosition().getPositionX() < boarderDistance){
				resultVector.addition(new Vector(turnfactor, 0));
			}
			if (this.getPosition().getPositionX() > this.boidWorld.getxLength() - boarderDistance){
				resultVector.addition(new Vector(-turnfactor, 0));
			}
			if (this.getPosition().getPositionY() < boarderDistance){
				resultVector.addition(new Vector(0, turnfactor));
			}
			if (this.getPosition().getPositionY() > this.boidWorld.getyHeight() - boarderDistance){
				resultVector.addition(new Vector(0, -turnfactor));
			}

		}
		return resultVector;
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
	/**
	 * The move method shall move the boid according to the rules the class sets.
	 * <b>Poid:</b> <i>Shall move towards any non poid-oid objects, and kill them.</i>
	 * <b>Hoid:</b> <i>Shall follow the classic Boids algorithm, Seperation, Cohision and Alignment.</i>
	 * <b>PlayerBoid:</b> <i>Shall follow the last known coordinates of the mouse cursor.</i>
	 */
	public abstract void move();

}