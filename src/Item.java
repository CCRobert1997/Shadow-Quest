import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Item extends GameObject {
	
	private boolean pickedUp;
	
	/**
	 * Initialises an item with the game.
	 * @param x The x coordinate of the item.
	 * @param y The y coordinate of the item.
	 * @param image_path reference the image to be used for the item.
	 * @throws SlickException
	 */
	public Item(double x, double y, String image_path) 
	throws SlickException{
		super(x, y, image_path);
		pickedUp = false;
	}
	
	
	/**@return whether or not the player has picked up the item. 
	 */
	public boolean getPickedUp() {
		return pickedUp;
	}
	/**
	 * Set whether or not the player has picked up the item.
	 * @param pickedUp True if the item has been picked up.
	 */
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}


	/**Render the object, if it has not been picked up and if it is on screen. 
	 * @param g The Slick graphics object.
	 * @param cam The game camera object.
	 */
	public void render(Graphics g, Camera cam) {
        if(!cam.onScreen(this)){
    		
    	}else if(!pickedUp){
			//only render the item if it hasn't been picked up yet.
			double xinscreen = getX();
        	double yinscreen = getY(); 
        	getImg().drawCentered((float)xinscreen, (float)yinscreen);
		}
	}
	
	/**
	 * Player picks up this item and its properties are affected .
	 * @param unit The player.
	 */
	public abstract void pickUp(Player unit);
}