package boidsgame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PoidTest {
	@BeforeEach
	public void setup(){

		Vector zeroVector = new Vector(0, 0);
		Vector position = new Vector(10, 10);
		int zeroMaxVelocity = 0;
		int maxAcceleration = 0;
		Boid p1 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		Boid p2 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		Boid p3 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		Boid p4 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);

		Boid p5 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		Boid p6 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, myWorld, killRadius, AttractionToHoidsCoefficient, seperationCoefficient);
		Boid pp = new PlayerBoid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld, "Poid");
		Boid ph = new PlayerBoid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld, "Hoid");
		Boid h1 = new Hoid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld, cohesionCoefficient, seperationCoefficient, alignmentCoefficient);
		Boid h2 = new Hoid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, viewRangeRadius, isAlive, boidWorld, cohesionCoefficient, seperationCoefficient, alignmentCoefficient);

		List<Boid> allBoids = new ArrayList<>();
		allBoids.add(p1);
		allBoids.add(p2);
		allBoids.add(p3);
		allBoids.add(p4);
		allBoids.add(p5);
		allBoids.add(p6);
		allBoids.add(pp);
		allBoids.add(ph);
		allBoids.add(h1);
		allBoids.add(h2);

		World testWorld = new World(1000, 1000, allBoids);
	}
	@Test
	@DisplayName("Test constructor")
	public void testConstructor(){
		Boid testgoodPoid = new Poid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, 1, 1, 1);
		Boid testBadPoid = new Poid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, -1, -1, -1);
		assertEquals(1, ((Poid) testgoodPoid).getKillRadius(), "Does not construct right.");
		assertEquals(1, ((Poid) testgoodPoid).getAttractionToHoidsCoefficient(), "Does not construct right.");
		assertEquals(1, ((Poid) testgoodPoid).getSeperationCoefficient(), "Does not construct right.");
		
		assertEquals(0, ((Poid) testBadPoid).getKillRadius(), "The negativ value does not change to zero.");
		assertEquals(0, ((Poid) testBadPoid).getAttractionToHoidsCoefficient(), "The negativ value does not change to zero.");
		assertEquals(0, ((Poid) testBadPoid).getSeperationCoefficient(), "The negativ value does not change to zero.");
	}
	
	@Test
	@DisplayName("Shall test if it other boids are freindly or not")
	public void testIsFriendlyBoid(){
		assertTrue(p1.isFriendlyBoid(p2), "The Poid find the other boid unfriendly. ");
		assertTrue(p1.isFriendlyBoid(pp), "The Poid find the other boid unfriendly. ");
		assertFalse(p1.isFriendlyBoid(ph), "The Poid find the other hoid playerBoid friendly. ");
		assertFalse(p1.isFriendlyBoid(h1), "The Poid find the other hoid friendly. ");
	}


	@Test
	@DisplayName("Test if movement is right")
	public void testMove() {
		// Poid to close to other Poid Move away

		// Poid sees Hoid move towards it.

		// no close poids, no movement
		
	}

	@Test
	@DisplayName("Shall test if it kills hoid in and out of range")
	public void killAllCloseBoidThatIsNotPoidoid() {
		// Shall test if it kills hoid in and out of range
		// Poid cant move since maxVelocity = 0; so it only kills close hoid-oid.
		assertEquals(0, p1.getKillAmount(), "Should only have one kill after murder of one hoid.");
		p1.move();
		assertEquals(false, h1.isAlive(), "Hoid in range but not dead");
		assertEquals(1, p1.getKillAmount(), "Should only have one kill after murder of one hoid.");
		p1.move();
		assertEquals(true, h2.isAlive(), "Hoid out of range but got killed");
		assertEquals(1, p1.getKillAmount(), "Should only have one kill after murder of one hoid.");

		// Test if it kills Poids in range
		assertEquals(0, p2.getKillAmount(), "Should not have kills.");
		p2.move();
		assertEquals(true, p3.isAlive(), "Poid in range but dead");
		assertEquals(0, p2.getKillAmount(), "Should not have kills.");

		// Playerboid
		assertEquals(0, p4.getKillAmount(), "Should not have kills.");
		p4.move();
		p4.move();
		assertEquals(false, ph.isAlive(), "Herd-oid PlayerBoid in range but not dead");
		assertEquals(true, pp.isAlive(), "Poid-oid PlayerBoid in range but dead");
		assertEquals(1, p4.getKillAmount(), "Should have one kill after murdering Hoid-oid playerBoid.");
	}	
}
