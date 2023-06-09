package boidsgame;
public class Vector {
	private int positionX;
	private int positionY;
	/**
	 * The Vector shall represent a mathematical 2d vector. All values are accepted and does not need validation
	 * @param x X coordinate of the vector
	 * @param y Y coordinate of the vector
	 */
	public Vector(int x,int y){
		this.positionX = x;
		this.positionY = y;
	}

	/**
	 * Adds the second vector to the first vector.
	 * @param vectorAdded
	 */
	public void addition(Vector vectorAdded){
		this.positionX += vectorAdded.positionX;
		this.positionY += vectorAdded.positionY;
	}

	/**
	 * Adds the second vector to the first vector.
	 * @param vectorAdded
	 */
	public Vector additionNewVector(Vector vectorAdded){
		return new Vector(this.positionX + vectorAdded.positionX, this.positionY + vectorAdded.positionY);
	}
	/**
	 * Makes a new vector that is the subtraction by the this vector - vectorSubtracted. Does not mutate any of the vectors.
	 * @param vectorAdded Vector to subtract
	 * @return new Vector
	 */
	public Vector subtractionVector(Vector vectorSubtracted){
		return new Vector(this.positionX - vectorSubtracted.positionX, this.positionY - vectorSubtracted.positionY);
	}
	
	/**
	 * distenceBetweenVector finds the shortest vector form other vector to this vector in 2d space.
	 * @param vector
	 * @return The shortest vector from other vector to this vector.
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
		double newPosX = this.positionX * scalar;
		double newPosY = this.positionY * scalar;
		return new Vector((int) newPosX, (int) newPosY);
	}
	/**
	 * Shall scale the vector so the length is aproxomaly equal to the size.
	 * @param size double number
	 * @return vector with same lenght of size
	 */
	public Vector scalingVectorToSize(double size){
		try{
			if (this.length() == 0) throw new IllegalStateException("Division By zero");
			return scalingNewVector((size/(this.length())));
		} catch (IllegalStateException e){
			return this;
		}
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