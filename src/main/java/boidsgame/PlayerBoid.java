package boidsgame;

public class PlayerBoid extends Boid{
	// What does it need:
	private int killScore;
	private int killRadius;

	private long birthTime;
	private long lifeTime;
	private double movementToCursorVectorCoefficient = 1;
	private String gameMode;

	private double mouseX;	
	private double mouseY;

	public PlayerBoid(Vector position, Vector velocity, Vector acceleration, int maxVelocity, int maxAcceleration, int viewRangeRadius, boolean isAlive, World boidWorld, String gameMode){
		super(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld);
		try {
			valideGameMode(gameMode);
			this.gameMode = gameMode;
		} catch (IllegalArgumentException e) {
			this.gameMode = "Hoid";
		}
		this.birthTime = System.currentTimeMillis();
		this.lifeTime = 0l;
		this.killScore = 0;
		this.killRadius = 5;

	}
	/**
	 * Checks that the player playes a legal gameMode.
	 * @param gameMode either "Hoid" or "Poid".
	 * @throws IllegalArgumentException if gamemode is not a legal case.
	 */
	private void valideGameMode(String gameMode) throws IllegalArgumentException {
		if (gameMode == null) throw new IllegalArgumentException("Cant give null as argument");
		if (!gameMode.equals("Hoid") && !gameMode.equals("Poid")) throw new IllegalArgumentException("No legal argument given");
	}

	/**
	 * Updates the lifetime to the PlayerBoid. From inizilation to this moment.
	 */
	private void updateLifeTime(){
		long currentTime = System.currentTimeMillis();
		this.lifeTime = currentTime - this.birthTime;
	}
	/**
	 *  This method will find the Vector towards the mouse cursor. If the coordinates are not inside the boidWorld they will not count. This info will be changed by an eventlistner. 
	 * @param mouseX The coordinate of the last known mouse position.
	 * @param mouseY The coordinate of the last known mouse position.
	 * @return Vector in the direction of the mouse pointer
	 */
	private Vector movementToCursorVector(double mouseX, double mouseY){
		if ((this.boidWorld.getxLength() >= mouseX && 0 <= mouseX) && (this.boidWorld.getyHeight() >= mouseY && 0 <= mouseY)){
			return this.getPosition().distenceBetweenVector(new Vector((int) mouseX, (int) mouseY));	
		}
		else {
			return new Vector(0, 0);
		}
	}

	/**
	 * Method to kill all boid in killRange and increment killScore for each kill:
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
		// Removes acceleration from last iteration
		this.setAcceleration(new Vector(0, 0));
		// Adds forces to acceleration
		this.acceleration.addition(this.movementToCursorVector(this.getMouseX(), this.getMouseY()));
		// change speed depending on acceleration
		// Make certain it can not go faster then maxVelocity
		this.velocity.addition(this.getAcceleration().scalingNewVector(movementToCursorVectorCoefficient));
		this.limitVelocity();
		// Move boid
		this.position.addition(this.getVelocity());
		// Kill boids in direct vicinity of the PlayerBoid if it is Poid-oid
		if (this.getGameMode().equals("Poid")){
			this.killAllCloseBoid();
		}
		this.updateLifeTime();
	}
	
	// SETTERS AND GETTERS
	public String getGameMode() {
		return gameMode;
	}

	public double getMouseX() {
		return mouseX;
	}
	public void setMouseX(double mouseX) {
		if (mouseX >= 0 && mouseX < this.boidWorld.getxLength()){
			this.mouseX = mouseX;
		}
	}
	public double getMouseY() {
		return mouseY;
	}
	public void setMouseY(double mouseY) {
		if (mouseY >= 0 && mouseY < this.boidWorld.getyHeight()){
			this.mouseY = mouseY;
		}
	}


	public int getKillScore() {
		return killScore;
	}
	public double getLifeTime() {
		return lifeTime/1000;
	}
	@Override
	public String toString(){
		return 
			"PlayerBoids x: " + this.getPosition().getPositionX() +" y: "+ this.getPosition().getPositionY() + 
			" Mouse: x: " + mouseX + " y: " + mouseY +
			" Velocity x: " + velocity.getPositionX() + " y: " + velocity.getPositionY() +
			" Acceleration x: " + acceleration.getPositionX() + " y: " + acceleration.getPositionY();
	}
	@Override
	public boolean isFriendlyBoid(Boid boid) {
		return false;
	}
	
}
