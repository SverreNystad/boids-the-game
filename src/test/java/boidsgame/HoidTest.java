package boidsgame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HoidTest {
	
	@Test
	@DisplayName("Shall test contructor")
	public void testContructor() {
		Boid goodHoid = new Hoid(new Vector(10, 10), new Vector(0, 0), new Vector(0, 0), 100, 100, 50, true, null, 1, 0, 0);
		Boid badHoid = new Hoid(new Vector(12, 8), new Vector(0, 0), new Vector(0, 0), 0, 0, 50, true, null, -1, -1, -10);

		assertEquals(0d, ((Hoid) badHoid).getCohesionCoefficient(), "The illegal amount did not get changed to zero.");
		assertEquals(0d, ((Hoid) badHoid).getSeperationCoefficient(), "The illegal amount did not get changed to zero.");
		assertEquals(0d, ((Hoid) badHoid).getAlignmentCoefficient(), "The illegal amount did not get changed to zero.");

	}

	@Test
	@DisplayName("This test will check if the function isFriendlyBoid give wright input")
	public void testIsFriendlyBoid() {
		Vector testVector = new Vector(0, 0);
		int testInt = 0;
		boolean alive = true;
		Boid poid = new Poid(testVector, testVector, testVector, testInt, testInt, testInt, alive, null, testInt, testInt, testInt);
		Hoid hoid = new Hoid(testVector, testVector, testVector, testInt, testInt, testInt, alive, null, testInt, testInt, testInt);
		Boid HoidPlayerBoid = new PlayerBoid(testVector, testVector, testVector, testInt, testInt, testInt, alive, null, "Hoid");
		Boid PoidPlayerBoid = new PlayerBoid(testVector, testVector, testVector, testInt, testInt, testInt, alive, null, "Poid");

		assertEquals(false, hoid.isFriendlyBoid(poid));
		assertEquals(true, hoid.isFriendlyBoid(HoidPlayerBoid));
		assertEquals(false, hoid.isFriendlyBoid(PoidPlayerBoid));
		assertEquals(true, hoid.isFriendlyBoid(hoid));
	}

	@Test
	@DisplayName("This test will check if the the boids move Cohesion")
	public void testMoveCohesion() {
		Vector velocity = new Vector(0, 0);
		Vector acceleration = new Vector(0, 0);

		Boid b1 = new Hoid(new Vector(10, 10), velocity, acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid b2 = new Hoid(new Vector(12, 8), velocity, acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid b3 = new Hoid(new Vector(8, 9), velocity, acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid b4 = new Hoid(new Vector(9, 12), velocity, acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid b5 = new Hoid(new Vector(20, 20), velocity, acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid outOfRangeHoid = new Hoid(new Vector(200, 200), velocity, acceleration, 100, 100, 50, true, null, 1, 0, 0);
		List<Boid> allBoids = new ArrayList<>();
		allBoids.add(b1);
		allBoids.add(b2);
		allBoids.add(b3);
		allBoids.add(b4);
		allBoids.add(b5);
		allBoids.add(outOfRangeHoid);
		
		World testWorld = new World(1000, 1000, null);
		testWorld.setAllInitBoids(allBoids);

		assertTrue(((Hoid) b1).cohesionVector(b1.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(2, 2)), "Unexpected amount for cohesion");
		assertTrue(((Hoid) b2).cohesionVector(b2.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(-1, 4)), "Unexpected amount for cohesion");
		assertTrue(((Hoid) b3).cohesionVector(b3.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(4, 3)), "Unexpected amount for cohesion");
		assertTrue(((Hoid) b4).cohesionVector(b4.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(3, -1)), "Unexpected amount for cohesion");
		assertTrue(((Hoid) b5).cohesionVector(b5.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(-11, -11)), "Unexpected amount for cohesion");
		assertTrue(((Hoid) outOfRangeHoid).cohesionVector(outOfRangeHoid.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(0, 0)), "Unexpected amount for cohesion. It should not see any other boids");
	}

	@Test
	@DisplayName("This test will check if the the boids aligmentvector is correct")
	public void testAlignmentVector() {
		Vector velocity = new Vector(0, 0);
		Vector acceleration = new Vector(0, 0);

		Boid b1 = new Hoid(new Vector(10, 10), new Vector(-1, 5), acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid b2 = new Hoid(new Vector(12, 8), new Vector(4, 5), acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid b3 = new Hoid(new Vector(8, 9), new Vector(-4, 1), acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid b4 = new Hoid(new Vector(9, 12), new Vector(0, 0), acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid b5 = new Hoid(new Vector(20, 20), new Vector(4, 5), acceleration, 100, 100, 50, true, null, 1, 0, 0);
		Boid outOfRangeHoid = new Hoid(new Vector(200, 200), new Vector(4, 5), acceleration, 100, 100, 50, true, null, 1, 0, 0);
		List<Boid> allBoids = new ArrayList<>();
		allBoids.add(b1);
		allBoids.add(b2);
		allBoids.add(b3);
		allBoids.add(b4);
		allBoids.add(b5);
		allBoids.add(outOfRangeHoid);
		
		World testWorld = new World(1000, 1000, null);
		testWorld.setAllInitBoids(allBoids);

		assertTrue(((Hoid) b1).alignmentVector(b1.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(5, 6)), "Unexpected amount for Alignment. Should steer towards its commen direction");
		assertTrue(((Hoid) b2).alignmentVector(b2.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(-5, 6)), "Unexpected amount for Alignment. Should steer towards its commen direction");
		assertTrue(((Hoid) b3).alignmentVector(b3.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(11, 14)), "Unexpected amount for Alignment. Should steer towards its commen direction");
		assertTrue(((Hoid) b4).alignmentVector(b4.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(3, 16)), "Unexpected amount for Alignment. Should steer towards its commen direction");
		assertTrue(((Hoid) b5).alignmentVector(b5.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(-5, 6)), "Unexpected amount for Alignment. Should steer towards its commen direction");
		assertTrue(((Hoid) outOfRangeHoid).alignmentVector(outOfRangeHoid.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(0, 0)), "Unexpected amount for Alignment. It should not see any other boids");
	}

	@Test
	@DisplayName("This test will check if the the boids aligmentvector is correct")
	public void testScareVector() {
		Vector velocity = new Vector(0, 0);
		Vector acceleration = new Vector(0, 0);

		Boid b1 = new Hoid(new Vector(10, 10), velocity, acceleration, 100, 100, 50, true, null, 0, 1, 0);
		Boid b2 = new Hoid(new Vector(17, 8), velocity, acceleration, 100, 100, 50, true, null, 0, 1, 0);
		Boid b3 = new Hoid(new Vector(14, 10), velocity, acceleration, 100, 100, 50, true, null, 0, 1, 0);
		Boid b4 = new Hoid(new Vector(9, 12), velocity, acceleration, 100, 100, 50, true, null, 0, 1, 0);
		Boid b5 = new Hoid(new Vector(20, 20), velocity, acceleration, 100, 100, 50, true, null, 0, 1, 0);
		Boid predetor = new Poid(new Vector(15, 10), velocity, acceleration, 100, 100, 50, true, null, 10, 0, 0);
		Boid outOfRangeHoid = new Hoid(new Vector(200, 200), new Vector(4, 5), acceleration, 100, 100, 50, true, null, 0, 1, 0);
		List<Boid> allBoids = new ArrayList<>();
		allBoids.add(b1);
		allBoids.add(b2);
		allBoids.add(b3);
		allBoids.add(b4);
		allBoids.add(b5);
		allBoids.add(predetor);
		allBoids.add(outOfRangeHoid);
		
		World testWorld = new World(1000, 1000, null);
		testWorld.setAllInitBoids(allBoids);

		assertTrue(((Hoid) b1).scareVector(b1.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(0, 0)), "Unexpected amount for ScareVector");
		assertTrue(((Hoid) b2).scareVector(b2.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(0, 0)), "Unexpected amount for ScareVector");
		assertTrue(((Hoid) b3).scareVector(b3.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(0, 0)), "Unexpected amount for ScareVector");
		assertTrue(((Hoid) b4).scareVector(b4.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(0, 0)), "Unexpected amount for ScareVector");
		assertTrue(((Hoid) b5).scareVector(b5.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(0, 0)), "Unexpected amount for ScareVector");
		assertTrue(((Hoid) outOfRangeHoid).scareVector(outOfRangeHoid.findAllBoidsInViewRange()).scalingNewVector(1).equals(new Vector(0, 0)), "Unexpected amount for ScareVector");
	}
	
}
