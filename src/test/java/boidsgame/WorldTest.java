package boidsgame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorldTest {

	@Test
	@DisplayName("Test all constructors")
	public void testValidWorld() {
		for (int number = 0; number < 20; number++) {
			new World(number, number, null);
			new World(number, number, null, true);
		}
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new World(-1, -1, null);
		}, "The world should not be possible");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new World(-1, -1, null, true);
		}, "The world should not be possible");
	}
	@Test
	@DisplayName("Test if one can set allinitBoids")
	public void testSetAllInitBoids(){
		World testNoBoids = new World(0, 0, null);
		Collection<Boid> emptyCollection = new ArrayList<>();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			testNoBoids.setAllInitBoids(emptyCollection);
		});
		
	}

	@Test
	@DisplayName("Tests if this world contains boids, poids and playerboid.")
	public void testInitGame() {
		int canvasLength = 100; 
		int canvasHeight = 100;
		String gameMode = "Poid";
		int startBoidsAmount = 100;
		int startPoidProsent = 50;
		Boolean wraparound = true;
		int poidViewRange = 60; 
		int killRadius = 5;
		double poidSeperationCoefficient = 1d; 
		double attractionToHoidsCoefficient = 1d; 
		int hoidViewRange = 40;
		double cohesionCoefficient = 1d; 
		double alignmentCoefficient = 1d; 
		double hoidSeperationCoefficient = 1d;
		World testInitGameWorld = World.initGame(canvasLength, canvasHeight, gameMode, startBoidsAmount, startPoidProsent, wraparound, poidViewRange, killRadius, poidSeperationCoefficient, attractionToHoidsCoefficient, hoidViewRange, cohesionCoefficient, alignmentCoefficient, hoidSeperationCoefficient);

		int initPlayerBoid = 0;
		int initPoids = 0;
		int initHoids = 0;

		for (Boid currentBoid : testInitGameWorld.getAllInitBoids()){
			if (currentBoid instanceof Poid) initPoids++;
			if (currentBoid instanceof Hoid) initHoids++;
			if (currentBoid instanceof PlayerBoid) initPlayerBoid++;
		}
		assertEquals((int) startBoidsAmount * startPoidProsent / 100, initPoids, "The world did not init right amount of Poids");
		assertEquals((int) startBoidsAmount - 1 - startBoidsAmount * startPoidProsent / 100, initHoids, "The world did not init right amount of Hoids");
		assertEquals(1, initPlayerBoid, "The world did not init right amount of PlayerBoids");


	}

	@Test
	@DisplayName("All movetests are in individual classes. Shall test if the mouse coordinates moves and if killing mainplayer check if changes worldsPlayerAlive.")
	public void testMoveAllBoids() {
		Boid player = new PlayerBoid(new Vector(0, 0), new Vector(10, 0), new Vector(0, 0), 10, 0, 0, true, null, "Hoid");
		ArrayList<Boid> onePlayerBoid = new ArrayList<>();
		onePlayerBoid.add(player);
		World playerWorld = new World(100, 100, null);
		playerWorld.setAllInitBoids(onePlayerBoid);
		assertTrue(playerWorld.getWorldsPlayerboid().isAlive(), "Expected the worlds player to be alive");
		assertTrue(player.getPosition().equals(new Vector(0, 0)), "Position should change");
		playerWorld.moveAllBoids();
		assertTrue(player.getPosition().equals(new Vector(10, 0)), "Position should change");
		player.setIsAlive(false);
		playerWorld.moveAllBoids();
		assertFalse(playerWorld.getWorldsPlayerboid().isAlive(), "Expected the worlds player to be dead");
		
		assertTrue(player.getPosition().equals(new Vector(10, 0)), "Position should not change");

	}

	@Test
	@DisplayName("Test if it can find the PlayerBoid")
	public void testGetWorldsPlayerboid(){
		World lonelyWorld = new World(0, 0, null);
		Boid lonelyPlayer = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "Hoid");
		Boid otherLonelyPlayer = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "Hoid");
		ArrayList<Boid> onePlayerBoid = new ArrayList<>();
		onePlayerBoid.add(lonelyPlayer);
		lonelyWorld.setAllInitBoids(onePlayerBoid);
		
		assertEquals(lonelyPlayer, lonelyWorld.getWorldsPlayerboid());
		assertNotEquals(otherLonelyPlayer, lonelyWorld.getWorldsPlayerboid(), "A playerboid not living in the world is equal the same as the one living in it");

	}
	@Test
	@DisplayName("Test if calculateAmountOfHoidsLeftAlive works")
	public void testCalculateAmountOfHoidsLeftAlive() {
		Boid HoidoidPlayerBoid = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "Hoid");
		Boid hoidTest = new Hoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, 1, 1, 1);
		Boid poidTest = new Poid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, 1, 1, 1);
		ArrayList<Boid> hoidList = new ArrayList<>();
		World testWorld = new World(0, 0, null);

		for (int i = 1; i < 100; i++){
			hoidList.add(hoidTest);
			hoidList.add(poidTest);
			hoidList.add(HoidoidPlayerBoid);
			testWorld.setAllInitBoids(hoidList);
			assertEquals(i, testWorld.calculateAmountOfHoidsLeftAlive(), "There are not as many Hoids as expected.");
		}
	}
	@Test
	@DisplayName("Test if WraparoundCoordinates works")
	public void testWraparoundCoordinates() {
		Boid hoidoidPlayerBoid = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "Hoid");
		Boid poidoidPlayerBoid = new PlayerBoid(new Vector(2, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "Poid");
		Boid hoidTest = new Hoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, 1, 1, 1);
		Boid poidTest = new Poid(new Vector(1, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, 0, 1, 1);

		ArrayList<Boid> boidList = new ArrayList<>();
		boidList.add(hoidoidPlayerBoid);
		boidList.add(poidoidPlayerBoid);
		boidList.add(hoidTest);
		boidList.add(poidTest);
		World testWorld = new World(100, 100, null, true);
		testWorld.setAllInitBoids(boidList);

		ArrayList<Vector> pointList = new ArrayList<>();
		Vector sVector = new Vector(0, 110);
		Vector nVector = new Vector(0, -10);
		Vector eVector = new Vector(110, 0);
		Vector wVector = new Vector(-10, 0);
		pointList.add(sVector);
		pointList.add(nVector);
		pointList.add(eVector);
		pointList.add(wVector);

		
		for (Vector vec : pointList) {
			for (Boid currentBoid : testWorld.getAllInitBoids()) {
				currentBoid.setPosition(vec);
				currentBoid.wraparoundCoordinates();
				if (vec.equals(sVector)){
					assertTrue(currentBoid.getPosition().equals(new Vector(0, 0)));
				}
				if (vec.equals(nVector)){
					assertTrue(currentBoid.getPosition().equals(new Vector(0, 100)));
				}
				if (vec.equals(eVector)){
					assertTrue(currentBoid.getPosition().equals(new Vector(0, 0)));
				}
				if (vec.equals(wVector)){
					assertTrue(currentBoid.getPosition().equals(new Vector(100, 0)));
				}	
			}
		}
	}

}
