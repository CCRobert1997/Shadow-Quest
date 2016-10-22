import org.newdawn.slick.SlickException;

public abstract class Monster extends Unit {
	
	/**
	 * Creates a new monster.
	 * @param x The initial x coordinate.
	 * @param y The initial y coordinate.
	 * @param image_path The reference of the image to be used for the monster.
	 * @param name The name of the monster.
	 * @param maxHP The maximum health points that the monster can have.
	 * @param speed The number of pixels the monster can move per millisecond.
	 * @throws SlickException
	 */
	public Monster(double x, double y, String image_path, String name, int maxHP, double speed)//, int removeid) 
	throws SlickException {
		
		super(x, y, image_path, name, maxHP, speed);
		
	}
	
	
	

	/**
	 * Update the monster's details in the world, including moving, attacking and managing timers.
	 * @param delta The number of milliseconds since the last frame.
	 * @param w The world of the game.
	 * @throws SlickException
	 */
	public abstract void update(int delta, World w) throws SlickException;
	
}
