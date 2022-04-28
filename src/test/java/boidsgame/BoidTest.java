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
		boidsAtSamePointWorld = new World(100, 100, initBoids);
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
	@DisplayName("")
	public void testFindAllBoidsInViewRange() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()) {
			Collection<Boid> tempBoids = initBoids.;
			tempBoids.remove(currentBoid);
			assertEquals(tempBoids, currentBoid.findAllBoidsInViewRange());;
			
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
	@DisplayName("")
	public void testGetMaxAcceleration() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(placeHolderNum, currentBoid.getMaxAcceleration());
		}
	}

	@Test
	@DisplayName("")
	public void testGetMaxVelocity() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(placeHolderNum, currentBoid.getMaxVelocity());
		}
	}

	@Test
	@DisplayName("")
	public void testGetPosition() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(posVec1, currentBoid.getPosition());
		}
	}

	@Test
	@DisplayName("")
	public void testGetVelocity() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(nullVector, currentBoid.getVelocity());
		}
	}

	@Test
	@DisplayName("")
	public void testGetViewRangeRadius() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(placeHolderNum, currentBoid.getViewRangeRadius());
		}
	}

	@Test
	@DisplayName("")
	public void testIsAlive() {
		for (Boid currentBoid : boidsAtSamePointWorld.getAllInitBoids()){
			assertEquals(true , currentBoid.isAlive());
		}
	}


	@Test
	@DisplayName("")
	public void testMove() {

	}

	@Test
	@DisplayName("")
	public void testSetAcceleration() {

	}

	@Test
	@DisplayName("")
	public void testSetIsAlive() {

	}

	@Test
	@DisplayName("")
	public void testSetMaxAcceleration() {

	}

	@Test
	@DisplayName("")
	public void testSetMaxVelocity() {

	}

	@Test
	@DisplayName("")
	public void testSetPosition() {

	}

	@Test
	@DisplayName("")
	public void testSetVelocity() {

	}

	@Test
	@DisplayName("")
	public void testSetViewRangeRadius() {

	}

	@Test
	@DisplayName("")
	public void testWraparoundCoordinates() {
		
	}

	@Test
	@DisplayName("")
	public void testWallScarVector() {
		
	}
}
