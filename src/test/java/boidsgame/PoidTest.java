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
	private Boid p1;
	private Boid p2;
	private Boid p3;
	private Boid p4;
	private Boid p5;
	private Boid p6;
	private Boid pp;
	private Boid ph;
	private Boid h1;
	private Boid h2;
	@BeforeEach
	public void setup(){
		Vector zeroVector = new Vector(0, 0);
		Vector position = new Vector(10, 10);
		Vector positionClose = new Vector(15, 15);

		Vector positionFarAway = new Vector(500, 500);
		Vector positionAtWorldsEnd = new Vector(1000, 1000);


		int zeroMaxVelocity = 0;
		int maxAcceleration = 0;
		int commenViewRange = 50;

		p1 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, 50, 1, 1);
		p2 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, 50, 1, 1);
		p3 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, 50, 1, 1);
		p4 = new Poid(positionFarAway, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, 50, 1, 1);
		p5 = new Poid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, 50, 1, 1);
		p6 = new Poid(positionClose, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, 50, 1, 1);
		pp = new PlayerBoid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, "Poid");
		ph = new PlayerBoid(positionFarAway, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, "Hoid");
		h1 = new Hoid(position, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, 0, 0, 0);
		h2 = new Hoid(positionAtWorldsEnd, zeroVector, zeroVector, zeroMaxVelocity, maxAcceleration, commenViewRange, true, null, 0, 0, 0);

		World testWorld = new World(1000, 1000, null);
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
		testWorld.setAllInitBoids(allBoids);

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
		World moveWorld = new World(1000, 1000, null);
		List<Boid> allMoveBoids = new ArrayList<>();
		Boid moveBoid1 = new Poid(new Vector(50, 50), new Vector(0, 0), new Vector(0, 0), 100, 100, 20, true, null, 1, 1, 1);
		Boid moveBoid2 = new Poid(new Vector(55, 50), new Vector(0, 0), new Vector(0, 0), 100, 100, 20, true, null, 1, 1, 1);
		Boid moveBoid3 = new Poid(new Vector(80, 80), new Vector(0, 0), new Vector(0, 0), 100, 100, 20, true, null, 1, 1, 0);
		Boid nonMovinghoid = new Hoid(new Vector(90, 80), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, 0, 0, 0);

		allMoveBoids.add(moveBoid1);
		allMoveBoids.add(moveBoid2);
		allMoveBoids.add(moveBoid3);
		allMoveBoids.add(nonMovinghoid);


		moveWorld.setAllInitBoids(allMoveBoids);
		
		
		// Poid to close to other Poid Move away
		moveBoid1.move();
		assertTrue(moveBoid1.getVelocity().equals(new Vector(-10, 0)), "Does not move correct");
		assertTrue(moveBoid1.getPosition().equals(new Vector(40, 50)), "Does not move correct");
		
		assertTrue(moveBoid2.getVelocity().equals(new Vector(0, 0)), "Does mutate the other boid");
		assertTrue(moveBoid2.getPosition().equals(new Vector(55, 50)), "Does mutate the other boid");

		// Poid sees Hoid move towards it.
		moveBoid3.move();
		assertTrue(moveBoid3.getVelocity().equals(new Vector(10, 0)), "Does not move correct");
		assertTrue(moveBoid3.getPosition().equals(new Vector(90, 80)), "Does not move correct");
		
		assertTrue(nonMovinghoid.getVelocity().equals(new Vector(0, 0)), "Does mutate the other boid");
		assertTrue(nonMovinghoid.getPosition().equals(new Vector(90, 80)), "Does mutate the other boid");

		// no close poids, no movement
		moveBoid3.setVelocity(new Vector(0, 0));
		moveBoid3.move();
		assertTrue(moveBoid3.getPosition().equals(new Vector(90, 80)), "Moves when there are no forces");
		
	}

	@Test
	@DisplayName("Shall test if it kills hoid in and out of range")
	public void killAllCloseBoidThatIsNotPoidoid() {
		// Shall test if it kills hoid in and out of range
		// Poid cant move since maxVelocity = 0; so it only kills close hoid-oid.
		assertEquals(0, ((Poid) p1).getKillAmount(), "Should only have one kill after murder of one hoid.");
		p1.move();
		assertEquals(false, h1.isAlive(), "Hoid in range but not dead");
		assertEquals(1, ((Poid) p1).getKillAmount(), "Should only have one kill after murder of one hoid.");
		p1.move();
		assertEquals(true, h2.isAlive(), "Hoid out of range but got killed");
		assertEquals(1, ((Poid) p1).getKillAmount(), "Should only have one kill after murder of one hoid.");

		// Test if it kills Poids in range
		assertEquals(0, ((Poid) p2).getKillAmount(), "Should not have kills.");
		p2.move();
		assertEquals(true, p3.isAlive(), "Poid in range but dead");
		assertEquals(0, ((Poid) p2).getKillAmount(), "Should not have kills.");

		// Playerboid
		assertEquals(0, ((Poid) p4).getKillAmount(), "Should not have kills.");
		p4.move();
		p4.move();
		assertEquals(false, ph.isAlive(), "Herd-oid PlayerBoid in range but not dead");
		assertEquals(true, pp.isAlive(), "Poid-oid PlayerBoid in range but dead");
		assertEquals(1, ((Poid) p4).getKillAmount(), "Should have one kill after murdering Hoid-oid playerBoid.");
	}	
}
