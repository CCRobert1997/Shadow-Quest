import org.newdawn.slick.SlickException;

public class Elvira extends Villager {

	/**
	 * Initialises a new healer within the game world.
	 * @param x The initial x coordinate of the healer Elvira.
	 * @param y The initial y coordinate of the healer Elvira.
	 * @param name The name of the healer Elvira.
	 * @throws SlickException
	 */
	public Elvira (double x, double y, String name)
	throws SlickException {
		super(x, y, "assets/units/shaman.png", name, 1, 0); 
	}
	
	
	/**
	 * Makes the healer talk and interact with the player.
	 * @param w The world of the game.
	 */
	public void interact(World w)
	throws SlickException {
		
		
		
		Player p = w.getPlayer();
		
		if(p.getHP() == p.getMaxHP())
			setDialogue("Return to me if you ever need healing.");
		else
		{
			p.setHP(p.getMaxHP());
			setDialogue("You're looking much healthier now.");
		}
		
		setDialoguetimepassed(0);
	}
}
