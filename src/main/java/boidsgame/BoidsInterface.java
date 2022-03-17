package boidsgame;

import java.util.Collection;

public interface BoidsInterface {
	
	public void move();
	
	public Vector getPosition();
	public Vector getVelocity();
	public Vector getAcceleration();
	public void setPosition(Vector newPosition);
	public void setVelocity(Vector newVelocity);
	public void setAcceleration(Vector newAcceleration);
	public boolean isAlive();
	
	public boolean boidInViewRange(BoidsInterface otherBoid);
	public Collection<BoidsInterface> findAllBoidsInViewRange(Collection<BoidsInterface> allBoids);
}
