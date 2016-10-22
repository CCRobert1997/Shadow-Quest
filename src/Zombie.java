import org.newdawn.slick.SlickException;

public class Zombie extends AggressiveMonster{
	
	/**
	 * Creates a new zombie in the game.
	 * @param x The initial x coordinate of the zombie.
	 * @param y The initial y coordinate of the zombie.
	 * @throws SlickException
	 */
	public Zombie(double x, double y)
	throws SlickException {
		
		super(x, y, "assets/units/zombie.png", "Zombie", 60, 800, 10);
	}
}
