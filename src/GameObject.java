import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
public abstract class GameObject {
	private double x, y;
	private Image img;
	
	/**
	 * Initialises a game object.
	 * @param x initials x coordinate of the game object.
	 * @param y initials y coordinate of the game object.
	 * @param image_path The file of the image to be used for the game object.
	 * @throws SlickException
	 */
	public GameObject(double x, double y, String image_path) 
			throws SlickException{
		this.x = x;
		this.y = y;
		this.img = new Image(image_path);
	}
	/**
	 * @return x coordinate
	 */
	public double getX() {
		return x;
	}
	/**
	 * Set x coordinate
	 * @param x new value of x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return y coordinate
	 */
	public double getY() {
		return y;
	}
	/**
	 * Set y coordinate
	 * @param y new value of y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * @return image of this object
	 */
	public Image getImg() {
		return img;
	}
	/**
	 * Give this object a new image
	 * @param img new image of this object
	 */
	public void setImg(Image img) {
		this.img = img;
	}
	
	/** 
	 * Calculates the distance between this and another game object.
	 * In this game, this always calculates distance with player.
	 * @param object always player in this game
	 * @return distance fron the player to this object
	 */
	public double calculateDistance(GameObject object){
		return Math.sqrt(Math.pow(object.getX() - this.getX(), 2) + Math.pow(object.getY() - this.getY(), 2));
	}
	/**
	 * 
	 * @param g graph
	 * @param cam camera
	 */
	public abstract void render(Graphics g, Camera cam);

}
