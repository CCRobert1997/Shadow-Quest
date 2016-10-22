/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Your name> <Your login>
 */

import org.newdawn.slick.SlickException;

/** Represents the camera that controls our viewpoint.
 */
public class Camera
{

    /** The unit this camera is following */
    private Player unitFollow;
    
    /** The width and height of the screen */
    /** Screen width, in pixels. */
    private int screenwidth;
    /** Screen height, in pixels. */
    private int screenheight;

    /** The camera's position in the world, in x and y coordinates. */
    private int xPos;
    private int yPos;

    /** Create a new Camera object.
     * 
     * @param player The player to follow
     * @param screenwidth The width of the viewport in pixels
     * @param screenheight The height of the viewport in pixels
     */
    public Camera(Player player, int screenwidth, int screenheight)
    {   
        this.unitFollow = player;
        this.screenwidth = screenwidth;
        this.screenheight = screenheight;
    }

    /** Update the game camera to re-centre its viewpoint around the player 
     */
    public void update()
    throws SlickException
    {
           // Update the camera based on the player's position
        xPos = (int) unitFollow.getX() - (screenwidth/2);
        yPos = (int) unitFollow.getY() - (screenheight/2); 
    }
        
    /** Returns the minimum x value on screen 
     * @return MinX
     */
    public int getMinX(){
        return xPos;
    }
    
    /** Returns the maximum x value on screen 
     * @return MaxX
     */
    public int getMaxX(){
        return xPos + screenwidth;
    }
    
    /** Returns the minimum y value on screen 
     * @return MinY
     */
    public int getMinY(){
        return yPos;
    }
    
    /** Returns the maximum y value on screen 
     * @return MaxY
     */
    public int getMaxY(){
        return yPos+screenheight;
    }

    /** Tells the camera to follow a given unit.
     * 
     * @param unit The new unit to follow
     */
    public void followUnit(Object unit)
    {
        if(unit instanceof Player){
            unitFollow = (Player) unit;
        }
    }
    
    /** Check an object whether is on screen. Rendering the object only if it is on screen
     * @param object The object need to be checked
     * @return whether on screen
     */
    public boolean onScreen(GameObject object) {
    	if(object.getX()>this.getMinX()
    			&& object.getX()<this.getMaxX()
    			&& object.getY()>this.getMinY()
    			&& object.getY()<this.getMaxY())
    		return true;
    	else
    		return false;
    }

    /** @Returns the xPos value
     */
	public int getxPos() {
		return xPos;
	}
	/** Set the xPos value
     */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/** @Returns the yPos value
     */
	public int getyPos() {
		return yPos;
	}

	/** Set the yPos value
     */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
    
}