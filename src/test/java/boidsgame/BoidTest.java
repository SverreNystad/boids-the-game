package boidsgame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoidTest {
	// Should make mock exsampels. 
	private Collection<Boid> initBoids;
	private World boidsAtSamePointWorld;
	private Vector posVec1;
	private Vector nullVector;
	private Hoid hoid1;
	private Poid poid1;
	private PlayerBoid playerBoidHoid1;
	private PlayerBoid playerBoidPoid1;
	private int placeHolderNum;
	private Vector posVec2;
	private Hoid hoid2;
	private Poid poid2;
	private PlayerBoid playerBoidHoid2;
	private PlayerBoid playerBoidPoid2;


	@BeforeEach
	public void setUp(){
		initBoids = new ArrayList<>();
		boidsAtSamePointWorld = new World(1000, 1000, initBoids);
		posVec1 = new Vector(50, 50);
		posVec2 = new Vector(100, 100);

		nullVector = new Vector(0, 0);
		placeHolderNum = 10;
		hoid1 = new Hoid(posVec1, nullVector, nullVector, placeHolderNum, placeHolderNum, placeHolderNum, true , boidsAtSamePointWorld, 1, 1, 1);
		poid1 = new Poid(posVec1, nullVector, nullVector, placeHolderNum, placeHolderNum, placeHolderNum, true , boidsAtSamePointWorld, 0, 1, placeHolderNum);
		playerBoidHoid1 = new PlayerBoid(posVec1, nullVector, nullVector, placeHolderNum, placeHolderNum, placeHolderNum, true , boidsAtSamePointWorld, "Hoid");
		playerBoidPoid1 = new PlayerBoid(posVec1, nullVector, nullVector, placeHolderNum, placeHolderNum, placeHolderNum, true , boidsAtSamePointWorld, "Poid");

		hoid2 = new Hoid(posVec2, nullVector, nullVector, placeHolderNum, placeHolderNum, placeHolderNum, true , boidsAtSamePointWorld, 1, 1, 1);
		poid2 = new Poid(posVec2, nullVector, nullVector, placeHolderNum, placeHolderNum, placeHolderNum, true , boidsAtSamePointWorld, 0, 1, placeHolderNum);
		playerBoidHoid2 = new PlayerBoid(posVec2, nullVector, nullVector, placeHolderNum, placeHolderNum, placeHolderNum, true , boidsAtSamePointWorld, "Hoid");
		playerBoidPoid2 = new PlayerBoid(posVec2, nullVector, nullVector, placeHolderNum, placeHolderNum, placeHolderNum, true , boidsAtSamePointWorld, "Poid");
		initBoids.add(hoid1);
		initBoids.add(poid1);
		initBoids.add(playerBoidHoid1);
		initBoids.add(playerBoidPoid1);
		boidsAtSamePointWorld.setAllInitBoids(initBoids);
	}

	@Test
	@DisplayName("Tests if each boid can see each other")
	public void testBoidInViewRange() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()) {
			for (Boid boid : initBoids) {
				assertTrue(currentBoid.boidInViewRange(boid));
			}
		}
		assertFalse(hoid2.boidInViewRange(hoid1));
	}

	@Test
	@DisplayName("Test if findALlBoidsInviewRange is the expected amount")
	public void testFindAllBoidsInViewRange() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()) {
			assertFalse(currentBoid.findAllBoidsInViewRange().contains(this), "This boid should not be in list");
			assertEquals(3, currentBoid.findAllBoidsInViewRange().size(), "The amount is not correct");
		}
		// assertFalse(hoid2.boidInViewRange(hoid1));
	}

	@Test
	@DisplayName("Test getter for acceleration")
	public void testGetAcceleration() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(nullVector, currentBoid.getAcceleration());
		}

	}

	@Test
	@DisplayName("Test if GetMaxAcceleration gives right amount")
	public void testGetMaxAcceleration() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(placeHolderNum, currentBoid.getMaxAcceleration());
		}
	}

	@Test
	@DisplayName("Test if GetMaxVelocity gives right amount")
	public void testGetMaxVelocity() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(placeHolderNum, currentBoid.getMaxVelocity());
		}
	}

	@Test
	@DisplayName("Test if GetPosition gives right amount")
	public void testGetPosition() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(posVec1, currentBoid.getPosition());
		}
	}

	@Test
	@DisplayName("Test if GetVelocity gives right amount")
	public void testGetVelocity() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(nullVector, currentBoid.getVelocity());
		}
	}

	@Test
	@DisplayName("Test if GetViewRangeRadius gives right amount")
	public void testGetViewRangeRadius() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(placeHolderNum, currentBoid.getViewRangeRadius());
		}
	}

	@Test
	@DisplayName("Test if isAlive works")
	public void testIsAlive() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(true , currentBoid.isAlive());
		}
	}

	@Test
	@DisplayName("Test if the wall scare gives right vector")
	public void testWallScarVector() {
		hoid1.setPosition(new Vector(0, 0));
		assertTrue(hoid1.wallScarVector().equals(new Vector(45, 45)), "Should be a force south east");
		hoid1.setPosition(new Vector(hoid1.getBoarderDistance(), hoid1.getBoarderDistance()));
		assertTrue(hoid1.wallScarVector().equals(new Vector(0, 0)), "Should not be any force");
	}
	@Test
	@DisplayName("Test if the one can give false input to setTurnfactor")
	public void testSetTurnfactor() {
		hoid1.setTurnfactor(-10);
		assertEquals(0, hoid1.getTurnfactor());
	}


	
}
