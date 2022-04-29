package boidsgame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerBoidTest {
	
	@Test
	@DisplayName("Shall test if contructor with bad input will change it")
	public void testPlayerBoidContructor() {
		Boid badPlayBoid = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "badInput");
		assertTrue(((PlayerBoid) badPlayBoid).getGameMode().equals("Hoid"), "GameMode should be Hoid");
	}

	@Test
	@DisplayName("")
	public void testGetLifeTime() {

	}

	@Test
	@DisplayName("")
	public void testMove() {
		Boid badPlayBoid = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "badInput");

	}

	@Test
	@DisplayName("")
	public void testSetMouseX() {
		Boid mousePlayer = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "Hoid");
		World playeWorld = new World(100, 200, null);
		List<Boid> playerList = new ArrayList<>();
		playerList.add(mousePlayer);
		playeWorld.setAllInitBoids(playerList);
		
		((PlayerBoid) mousePlayer).setMouseX(50);
		assertEquals(50, ((PlayerBoid) mousePlayer).getMouseX());
		((PlayerBoid) mousePlayer).setMouseX(250);
		assertEquals(50, ((PlayerBoid) mousePlayer).getMouseX());




	}

	@Test
	@DisplayName("")
	public void testSetMouseY() {
		Boid mousePlayer = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "Hoid");
		World playeWorld = new World(100, 200, null);
		List<Boid> playerList = new ArrayList<>();
		playerList.add(mousePlayer);
		playeWorld.setAllInitBoids(playerList);
		
		((PlayerBoid) mousePlayer).setMouseX(50);
		assertEquals(50, ((PlayerBoid) mousePlayer).getMouseX());
		((PlayerBoid) mousePlayer).setMouseX(250);
		assertEquals(50, ((PlayerBoid) mousePlayer).getMouseX());

	}
}
