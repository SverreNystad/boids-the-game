package boidsgame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VectorTest {
	Vector v1;
	Vector v2;
	Vector v3;
	Vector v4;

	@BeforeEach
	public void setup() {
		v1 = new Vector(100,100);
		v2 = new Vector(90,10);
		v3 = new Vector(4,3);
		v4 = new Vector(-10,-10);
	}

	@Test
	@DisplayName("This test will check the vectors constructor.")
	public void testConstructor() {
		Vector testVector = new Vector(1,1);
	}

	@Test
	@DisplayName("This test will check the vectors getters.")
	public void testGetPosition() {
		// X cord
		assertEquals(100, v1.getPositionX());
		assertEquals(90, v2.getPositionX());
		assertEquals(4, v3.getPositionX());
		// Y cord
		assertEquals(100, v1.getPositionY());
		assertEquals(10, v2.getPositionY());
		assertEquals(3, v3.getPositionY());
	}

	@Test
	@DisplayName("This test will check the vectors equal method.")
	public void testEqual() {
		assertTrue(v1.equals(v1));
		assertFalse(v1.equals(v2));
	}

	@Test
	@DisplayName("This test will check the vectors additon.")
	public void testAddition() {
		v1.addition(v2);
		// v1 should be muteted
		assertEquals(190, v1.getPositionX());
		assertEquals(110, v1.getPositionY());
		assertTrue(new Vector(190,110).equals(v1));
		// v2 should not be muteted
		assertEquals(90, v2.getPositionX());
		assertEquals(10, v2.getPositionY());
		assertTrue(new Vector(90,10).equals(v2));

		// check negativ numbers:
		v4.addition(v3);
		assertTrue(new Vector(-6,-7).equals(v4), "Not adding accordingly");
		// Adding negativ numbers:
		v3.addition(v4);
		assertTrue(new Vector(-2,-4).equals(v3), "Not adding accordingly");
		// assertEquals(int[] {190},int[] {v1.getPositionX(),v1.getPositionY());
	}
	@Test
	@DisplayName("This test will check the vectors additon.")
	public void testAdditionNewVector() {
		assertTrue(new Vector(190, 110).equals(v1.additionNewVector(v2)), "The new vector is not equal to exspected output");
		assertTrue(new Vector(190, 110).equals(v2.additionNewVector(v1)), "The new vector is not equal to exspected output");
		assertTrue(new Vector(80, 0).equals(v2.additionNewVector(v4)), "The new vector is not equal to exspected output");
	}

	@Test
	@DisplayName("This test will check if addSeveralVectors cam add several vectors ")
	public void testAddSeveralVectors() {
		// System.out.println((v1.addSeveralVectors(v2, v3, v4)));
		assertTrue((v1.addSeveralVectors(v2, v3, v4)).equals(new Vector(184, 103)));
	}

	@Test
	@DisplayName("This test will check if testSubtractionVector can subtract")
	public void testSubtractionVector() {
		assertTrue(new Vector(10, 90).equals(v1.subtractionVector(v2)), "The new vector is not equal to exspected output");
		assertTrue(new Vector(-10, -90).equals(v2.subtractionVector(v1)), "The new vector is not equal to exspected output");
		assertTrue(new Vector(100, 20).equals(v2.subtractionVector(v4)), "The new vector is not equal to exspected output");
	}
	@Test
	@DisplayName("This test will check if the length of a vector is correct.")
	public void testLength(){
		assertEquals(5, v3.length());
	}

	@Test
	@DisplayName("This test will check if the distenceBetweenVector gives right vektor")
	public void testDistenceBetweenVector(){
		// System.out.println(v1.distenceBetweenVector(v2).getPositionX());
		// System.out.println(v1.distenceBetweenVector(v2).getPositionY());
		assertTrue((v1.distenceBetweenVector(v2)).equals(new Vector(-10, -90)), "The distance is not correct");
		// assertEquals(expected, v1.distenceBetweenVector(v2));
	}

	@Test
	@DisplayName("This test will check if the scaling gives right vektor")
	public void testScaling() {
		assertTrue(v1.equals(new Vector(100, 100)), "The vector is not correct");
		v1.scaling(2);
		assertTrue(v1.equals(new Vector(200, 200)), "The vector is not correct");

	}
	@Test
	@DisplayName("This test will check if the scalingNewVector gives right vektor")
	public void testScalingNewVector() {
		assertTrue(v1.scalingNewVector(1.5).equals(new Vector(150, 150)));
		assertTrue(v1.scalingNewVector(0.9).equals(new Vector(90, 90)));
	}

	@Test
	@DisplayName("This test will check if the scalingVectorToSize gives right vektor")
	public void testScalingVectorToSize() {
		for (int i = 0; i < 10; i++){	// It returns almost the vector that would have the wanted length. If one round the number it becomes right
			assertTrue(v1.scalingVectorToSize(i).length() <= i, "The vector is larger then size"); 
			assertTrue(v2.scalingVectorToSize(i).length() <= i, "The vector is larger then size"); 
			assertTrue(v3.scalingVectorToSize(i).length() <= i, "The vector is larger then size"); 
			assertTrue(v4.scalingVectorToSize(i).length() <= i, "The vector is larger then size"); 
		}

		assertTrue(v4.scalingVectorToSize(0).length() == 0, "Vector is not null after making it to size 0"); 
		assertTrue(new Vector(0, 0).scalingVectorToSize(10).length() == 0, "Vector is not 0 in length"); 
	}

}