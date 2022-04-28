package boidsgame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PoidTest {
	@BeforeEach
	public void setup(){
		// Poid p1 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		// Poid p2 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		// Poid p3 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		// Poid p4 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		// Poid p5 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		// Poid p6 = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);

		// PlayerBoid pp = new PlayerBoid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld, "Poid");
		// PlayerBoid ph = new PlayerBoid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld, "Hoid");
		// Hoid h1 = new Hoid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld, cohesionCoefficient, seperationCoefficient, alignmentCoefficient)

	}
	@Test
	@DisplayName("Test constructor")
	public void testConstructor(){
		// Poid testPoidConstructor = new Poid(position, velocity, acceleration, maxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, attractionToHoidsCoefficient, seperationCoefficient)

		Boid testgoodPoid = new Poid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, 1, 1, 1);
		Boid expectedOutcomeOfBadPoid = new Poid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, 0, 0, 0);
		Boid testBadPoid = new Poid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, -1, -1, -1);
		// System.out.println(testBadPoid.attractionToHoidsCoefficient);
		assertTrue(testBadPoid.equals(expectedOutcomeOfBadPoid));

	}
	
	@Test
	@DisplayName("Shall test if it other boids are freindly or not")
	public void testIsFriendlyBoid(){
		assertTrue(p1.isFriendlyBoid(p2));
		assertTrue(p1.isFriendlyBoid(pp));
		assertFalse(p1.isFriendlyBoid(ph));
		assertFalse(p1.isFriendlyBoid(h1));
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
