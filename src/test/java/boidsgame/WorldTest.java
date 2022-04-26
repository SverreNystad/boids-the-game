package boidsgame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorldTest {

	@Test
	@DisplayName("")
	public void testInitGame() {

	}

	@Test
	@DisplayName("")
	public void testMoveAllBoids() {

	}

	@Test
	@DisplayName("")
	public void testRemoveAllDeadBoids() {

	}


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
}
