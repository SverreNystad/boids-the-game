package boidsgame;

public class PlayerBoid extends Boid{
	// What does it need:
	private int killScore;
	private int killRadius;

	private double birthTime;
	private double lifeTime;
	private double movementToCursorVectorCoefficient;
	private String gameMode;

	private double mouseX;	
	private double mouseY;
	// TODO: Make contructor.
	public PlayerBoid(Vector position, Vector velocity, Vector acceleration, int maxVelocity, int maxAcceleration, int viewRangeRadius, boolean isAlive, World boidWorld, String gameMode){
		super(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld);
		this.gameMode = gameMode;
		this.birthTime = 0d;
		this.lifeTime = 0d;
		this.killScore = 0;
		this.killRadius = 5;

	}
	// TODO: Get info from eventlistener on Canvas and move accordingly.
	/**
	 *  This method will find the Vector towards the mouse cursor. If the coordinates are not inside the boidWorld they will not count. This info will be changed by an eventlistner. 
	 * @param mouseX The coordinate of the last known mouse position.
	 * @param mouseY The coordinate of the last known mouse position.
	 * @return Vector in the direction of the mouse pointer
	 */
	private Vector movementToCursorVector(double mouseX, double mouseY){
		if ((this.boidWorld.xLength >= mouseX && 0 <= mouseX) && (this.boidWorld.yHeight >= mouseY && 0 <= mouseY)){
			// return new Vector((int) (((double) this.getPosition().getPositionX()) - mouseX), (int) (((double) this.getPosition().getPositionY()) - mouseY));
			return this.getPosition().distenceBetweenVector(new Vector((int) mouseX, (int) mouseY));	
		}
		else {
			return new Vector(0, 0);
		}
	}
	/**
	 * Method to kill all boid in range and increment killScore.:
	 */
	private void killAllCloseBoid(){
		for (Boid currentBoid : this.findAllBoidsInViewRange()) {
			if (this.position.distenceBetweenVector(currentBoid.getPosition()).length() <= killRadius && currentBoid.isAlive){
				currentBoid.setIsAlive(false);
				this.killScore++;
			}
		}
	}
	
	@Override
	public void move(){
		this.setAcceleration(new Vector(0, 0));
		this.acceleration = this.movementToCursorVector(this.getMouseX(), this.getMouseY());
		this.velocity = this.getAcceleration().scalingNewVector(movementToCursorVectorCoefficient);
		this.position.addition(this.getVelocity());
		if (this.getGameMode().equals("Hoid")){
			this.killAllCloseBoid();
		}
	}
	
	// SETTERS AND GETTERS
	public String getGameMode() {
		return gameMode;
	}

	public double getMouseX() {
		return mouseX;
	}
	public void setMouseX(double mouseX) {
		// TODO: Test this.
		if (this.mouseX >= 0 && this.mouseX < this.boidWorld.getxLength()){
			this.mouseX = mouseX;
		}
	}
	public double getMouseY() {
		return mouseY;
	}
	public void setMouseY(double mouseY) {
		// TODO: Test this.
		if (this.mouseX >= 0 && this.mouseX < this.boidWorld.getyHeight()){
			this.mouseY = mouseY;
		}
	}


	public int getKillScore() {
		return killScore;
	}
	// public void setKillScore(int killScore) {
	// 	this.killScore = killScore;
	// }
	public double getLifeTime() {
		return lifeTime;
	}
	// public void setLifeTime(double lifeTime) {
	// 	this.lifeTime = lifeTime;
	// }
	
}
