import org.newdawn.slick.SlickException;

public class Bandit extends AggressiveMonster{
			
	/**
	 * Creates a new bandit in the game.
	 * @param x The initial x coordinate of the bandit.
	 * @param y The initial y coordinate of the bandit.
	 * @throws SlickException
	 */
	public Bandit(double x, double y)
	throws SlickException
	{
		super(x, y, "assets/units/bandit.png", "Bandit", 40, 200, 8);
	}
}
