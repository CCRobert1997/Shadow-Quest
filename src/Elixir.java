import org.newdawn.slick.SlickException;

public class Elixir extends Item{
	
	/**
	 * Initialises an elixir in the game world.
	 * @param x The x coordinate of the object.
	 * @param y The y coordinate of the object.
	 * @throws SlickException
	 */
	public Elixir(double x, double y) 
	throws SlickException{
		super(x, y, "assets/items/elixir.png");
	}
	
	/**
	 * pick up Elixir.
	 * @param unit The player.
	 */
	public void pickUp(Player unit) {
		setPickedUp(true);
		unit.getInventory().add(this);
	}

}
