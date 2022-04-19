package boidsgame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HoidTest {
	
	// @BeforeEach
	// public void setup() {
	// 	int totalAmountOfBoids = 10;
	// 	Collection<Boid> allBoidsInLine;
	// 	Vector position = new Vector(0, 0);
	// 	Vector velocity = new Vector(0, 0);
	// 	Vector acceleration = new Vector(0, 0);
	// 	int maxVelocity = ;
	// 	int maxAcceleration = ;
	// 	int viewRangeRadius = ;
	// 	int cohesionCoefficient = ;
	// 	int seperationCoefficient = ;
	// 	int alignmentCoefficient
	// 	for (int i = 0; i < totalAmountOfBoids; i++){
	// 		allBoidsInLine.add(Hoid());
	// 	}
		
	// }

	@Test
	@DisplayName("This test will check if the function isFriendlyBoid give wright input")
	public void testIsFriendlyBoid() {
		Vector testVector = new Vector(0, 0);
		int testInt = 0;
		boolean alive = true;
		Boid poid = new Poid(testVector, testVector, testVector, testInt, testInt, testInt, alive, testInt, testInt);
		Hoid hoid = new Hoid(testVector, testVector, testVector, testInt, testInt, testInt, alive, testInt, testInt, testInt);
		Boid playerBoid = new PlayerBoid();

		assertEquals(true, hoid.isFriendlyBoid(playerBoid));



		
	}
}
