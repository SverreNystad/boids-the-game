package boidsgame;

import java.util.Collection;

public interface BoidsInterface {
	
	public void move();
	
	public Vector getPosition();
	public Vector getVelocity();
	public Vector getAcceleration();
	public void setPosition();
	public void setVelocity();
	public void setAcceleration();
	public void isAlive();
	
	public boolean boidInViewRange(BoidsInterface otherBoid);
	public Collection<BoidsInterface> findAllBoidsInViewRange(Collection<BoidsInterface> allBoids);
}
