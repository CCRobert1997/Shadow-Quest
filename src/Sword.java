import org.newdawn.slick.SlickException;

public class Sword extends Item {
	
	public static final int DAMAGEINCREASE = 30;
	
	/**
	 * Initialise a sword object in the world.
	 * @param x The x coordinate of the sword.
	 * @param y The y coordinate of the sword.
	 * @throws SlickException
	 */
	public Sword(double x, double y)
	throws SlickException{
		super(x, y, "assets/items/sword.png");
	}
	
	
	/**Increase the player's maxDamage and set pickedup true. 
	 * @param unit The player. 
	 */
	public void pickUp(Player unit) {
		unit.setMaxDamage(unit.getMaxDamage() + DAMAGEINCREASE);
		setPickedUp(true);
		unit.getInventory().add(this);
	}
}
