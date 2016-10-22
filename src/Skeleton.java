import org.newdawn.slick.SlickException;


public class Skeleton extends AggressiveMonster {
	
	/**
	 * Creates a new skeleton in the game.
	 * @param x The initial x coordinate of the skeleton.
	 * @param y The initial y coordinate of the skeleton.
	 * @throws SlickException
	 */
	public Skeleton(double x, double y)
	throws SlickException {
		
		super(x, y, "assets/units/skeleton.png", "Skeleton", 100, 500, 16);
	}
}
