import org.newdawn.slick.SlickException;


public class GiantBat extends PassiveMonster {
	
	/**
	 * Creates a new giant bat within the game world.
	 * @param x The initial x coordinate of the giant bat.
	 * @param y The initial y coordinate of the giant bat.
	 * @throws SlickException
	 */
	public GiantBat(double x, double y)//, int removeid)
	throws SlickException {
		
		super(x, y, "assets/units/dreadbat.png", "Giant Bat", 40);//, removeid);
	}

}
