import org.newdawn.slick.SlickException;

public class Tome extends Item{
	
	public static final int COOLDOWNDECREASE = -300;
	
	/**
	 * Initialise a tome in the world.
	 * @param x The x coordinate of the tome.
	 * @param y The y coordinate of the tome.
	 * @throws SlickException
	 */
	public Tome(double x, double y)
	throws SlickException {
		super(x, y, "assets/items/tome.png");
	}
	
	
	/**Reduce the player's cooldown and set pickedup true.
	 * @param unit The player picking up the Tome.
	 */
	public void pickUp(Player unit) {
		unit.setCooldown(unit.getCooldown() + COOLDOWNDECREASE);
		setPickedUp(true);
		unit.getInventory().add(this);
	}

}
