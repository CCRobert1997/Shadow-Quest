import org.newdawn.slick.SlickException;

public class PrinceAldric extends Villager {
	/**
	 * Creates a new Prince.
	 * @param x the x location of prince in pixel
	 * @param y the y location of prince in pixel
	 * @param name The name of the prince.
	 * @throws SlickException
	 */
	public PrinceAldric(double x, double y, String name)
			throws SlickException {
				super(x, y, "assets/units/prince.png", name, 1, 0); 
			}
	/**
	 * Interact with player.
	 * @param w the game world.
	 */
	public void interact(World w) {
		if(getDialoguetimepassed() < dialogueremaintime)
			return;
		
		Player p = w.getPlayer();
		
		setDialoguetimepassed(0);
		if (!p.getInventory().contains(w.getItems().get(3))){
			setDialogue("Please seek out the Elixir of Life to cure the king.");
		} else {
			p.getInventory().remove(w.getItems().get(3));
			setDialogue("The elixir! My father is cured! Thankyou!");
		}
		
	}
}
