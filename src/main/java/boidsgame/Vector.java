package boidsgame;
public class Vector {
	private int positionX;
	private int positionY;

	public Vector(int x,int y){
		if (isValidVector(x,y)){
			this.positionX = x;
			this.positionY = y;
		}
		else{
			throw new IllegalArgumentException("This vector is not valid.");
		}
	}
	/**
	 * The isValidVector method will check if the x and y coordinates are in the coordinatespace.
	 * @param x
	 * @param y
	 * @return valid
	 */
	private boolean isValidVector(int x, int y) { // World class must be finished
		// return (World.getWidth() >= x) ;
		return true;
	}
	/**
	 * Adds the second vector to the first vector.
	 * @param vectorAdded
	 */
	public void addition(Vector vectorAdded){
		this.positionX += vectorAdded.positionX;
		this.positionY += vectorAdded.positionY;
	}
	public Vector subtractionVector(Vector vectorAdded){
		return new Vector(this.positionX - vectorAdded.positionX, this.positionY - vectorAdded.positionY);
	}
	
	/**
	 * distenceBetweenVector finds the shortest vector form other vector to this vector in 2d space.
	 * @param vector
	 * @return The shortest vector form other vector to this vector.
	 */
	public Vector distenceBetweenVector(Vector vector){
		return new Vector(vector.positionX - this.positionX, vector.positionY - this.positionY);
	}

	/**
	 * Scaling can be used to scale a vector a double amount. Then with typecasting convert the double back to an integer. This method will only mutate the original vector.
	 * @param scalar
	 */
	public void scaling(double scalar){
		double newPosX = this.positionX *= scalar;
		double newPosY = this.positionY *= scalar;
		this.positionX = (int) newPosX;
		this.positionY = (int) newPosY;
	}
	/**
	 * scalingNewVector can be used to scale a vector a double amount. Then with typecasting convert the double back to an integer
	 * @param scalar
	 * @return A new vector based on the original vectors after scaling
	 */
	public Vector scalingNewVector(double scalar){
		double newPosX = this.positionX *= scalar;
		double newPosY = this.positionY *= scalar;
		return new Vector((int) newPosX, (int) newPosY);
	}

	/**
	 * The length method uses the pytagorean theorem to calculate the length of the vector.
	 * @return the length in double
	 */
	public double length(){
		return Math.sqrt((getPositionX()*getPositionX())+(getPositionY()*getPositionY()));
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int newPositionX) {
		this.positionX = newPositionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int newPositionY) {
		this.positionY = newPositionY;
	}
	// @Override 
	public boolean equals(Vector otherVector){
		return (this.getPositionX() == otherVector.getPositionX()) && (this.getPositionY() == otherVector.getPositionY());
	}
	public Vector addSeveralVectors(final Vector ... vectors){
		for (Vector vector : vectors) {
			this.addition(vector);
		}
		return this;
	}
	
}