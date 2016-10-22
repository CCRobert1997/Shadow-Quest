import org.newdawn.slick.SlickException;

public class Amulet extends Item {
	
	public static final int MAXHPINCREASE = 80;
	
	/**
	 * Initialises an amulet object in the world.
	 * @param x The x coordinate of the amulet.
	 * @param y The y coordinate of the amulet.
	 * @throws SlickException
	 */
	public Amulet(double x, double y) 
	throws SlickException{
		super(x, y, "assets/items/amulet.png");
	}
	
	
	/**Increase the player's MaxHP and set pickedup true. 
	 * @param unit The player.
	 */
	public void pickUp(Player unit) {
		unit.setMaxHP(unit.getMaxHP() + MAXHPINCREASE);
		unit.setHP(unit.getHP() + MAXHPINCREASE);
		setPickedUp(true);
		unit.getInventory().add(this);
	}
}
