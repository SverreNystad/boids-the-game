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
	@DisplayName("Test if playerboid moves towards the mouse ")
	public void testMove() {
		Boid player = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 100, 100, 100, true, null, "badInput");
		World playeWorld = new World(100, 200, null);
		List<Boid> playerList = new ArrayList<>();
		playerList.add(player);
		playeWorld.setAllInitBoids(playerList);

		((PlayerBoid) player).setMouseX(50);
		((PlayerBoid) player).setMouseY(50);

		player.move();
		assertTrue(player.getPosition().equals(new Vector(50, 50)));
	}

	@Test
	@DisplayName("Test if mousecoords works")
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
	@DisplayName("Test if mousecoords works")
	public void testSetMouseY() {
		Boid mousePlayer = new PlayerBoid(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), 0, 0, 0, true, null, "Hoid");
		World playeWorld = new World(100, 200, null);
		List<Boid> playerList = new ArrayList<>();
		playerList.add(mousePlayer);
		playeWorld.setAllInitBoids(playerList);
		
		((PlayerBoid) mousePlayer).setMouseY(50);
		assertEquals(50, ((PlayerBoid) mousePlayer).getMouseY());
		((PlayerBoid) mousePlayer).setMouseY(250);
		assertEquals(50, ((PlayerBoid) mousePlayer).getMouseY());
	}
}
