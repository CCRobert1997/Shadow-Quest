import org.newdawn.slick.SlickException;

public class Garth extends Villager {
	
	/**
	 * Initialises a farmer within the game world.
	 * @param x The initial x coordinate of the farmer.
	 * @param y The initial y coordinate of the farmer.
	 * @param name The name of the farmer.
	 * @throws SlickException.
	 */
	public Garth(double x, double y, String name)
	throws SlickException {
		super(x, y, "assets/units/peasant.png", name, 1, 0); 
	}
	
	
	/**
	 * Make the Garth talk and interact with the player.
	 * @param w The world of the game.
	 */
	public void interact(World w) {
		
		Player p = w.getPlayer();
		if(!p.getInventory().contains(w.getItems().get(2)))
			setDialogue("Find the Amulet of Vitality, across the river to the west.");
		else if(!p.getInventory().contains(w.getItems().get(1)))
			setDialogue("Find the Sword of Strength - cross the river and back, on the east side.");
		else if(!p.getInventory().contains(w.getItems().get(0)))
			setDialogue("Find the Tome of Agility, in the Land of Shadows.");
		else
			setDialogue("You have found all the treasure I know of.");
		
		setDialoguetimepassed(0);
	}
}
