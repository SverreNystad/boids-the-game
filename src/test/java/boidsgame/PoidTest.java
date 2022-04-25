package boidsgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PoidTest {
	@BeforeEach
	public void setup(){
		Poid p1 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, movementCoefficient);
		Poid p2 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, movementCoefficient);
		Poid p3 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, movementCoefficient);
		Poid p4 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, movementCoefficient);
		Poid p5 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, movementCoefficient);
		Poid p6 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, movementCoefficient);


	}

	@Test
	@DisplayName("")
	public void testMove() {
		
	}

	@Test
	@DisplayName("")
	public void killAllCloseBoidThatIsNotPoidoid() {
		
	}

	
}
