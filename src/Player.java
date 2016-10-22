/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Sample Solution
 * Author: Matt Giuca <mgiuca>
 */

//import org.newdawn.slick.Image;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;

/** The character which the user plays as.
 */
public class Player extends Unit
{
	
    
    private static final double SPEED = 0.25;
    private int maxDamage;
    
    // the colletion of items player has collected
    private ArrayList<Item> inventory = new ArrayList<Item>(0);
    
    private static final int RESTARTX = 738;
	private static final int RESTARTY = 549;
    
	/**
	 * @return the colletion of items player has collected
	 */
    public ArrayList<Item> getInventory() {
		return inventory;
	}

    /**
	 * @param inventory the colletion of items player has collected
	 */
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}



	
    

	/** Creates a new Player.
     * @param image_path Path of player's image file.
     * @param x The Player's starting x location in pixels.
     * @param y The Player's starting y location in pixels.
     */
    public Player(String image_path, double x, double y, int MAXHP, int maxdamage, int cooldown)
        throws SlickException
    {
    	super(x, y, "assets/units/player.png", "Player", MAXHP, SPEED); 
    	this.maxDamage = maxdamage;
    	setCooldown(0);
    	setTotalcooldowntime(cooldown);
    }

    /**
     * @return the max damage of the player.
     */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
     * @param maxDamage the max damage of the player.
     */
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}
	
	
	
	/**
	 * Moves the player around the map, taking into account checking map edges and terrain type.
	 * @param dir_x The x direction that the player is trying to move: -1 or 0 or 1.
	 * @param dir_y The y direction that the player is trying to move: -1 or 0 or 1.
	 * @param delta The number of milliseconds since the last frame.
	 * @param w game world.
	 * @param A_key True if the A key is being pressed.
	 * @param T_key True if the T key is being pressed.
	 * @throws SlickException
	 */
	public void update(double dir_x, double dir_y, int delta, World w, boolean A_key, boolean T_key) 
	throws SlickException {
		
		//If dead, respawn at village with full health
		if(getHP() <= 0) {
			backtovillage();
			return;
		}
		
		move(w, dir_x, dir_y, delta);
		
		setCooldown(getCooldown() - delta);
		// Attack 
		if(A_key && getCooldown()  <= 0) {
			for(Monster m : w.getMonsters()) {
				if(calculateDistance(m) <= 50) {
					attack(m);
				}
			}
		}
		//Talk with villager.
		if(T_key) {
			Villager v1 = w.getGarth();
			if(calculateDistance(v1) <= 50)
				v1.interact(w);
			Villager v2 = w.getHealer();
			if(calculateDistance(v2) <= 50)
				v2.interact(w);
			Villager v3 = w.getPrince();
			if(calculateDistance(v3) <= 50)
				v3.interact(w);
		}
		//Pick up items.
		for(Item i : w.getItems()) {
			if(calculateDistance(i) <= 50 && !i.getPickedUp()) {
				i.pickUp(this);
			}
		}
	}
	
	/**
	 * Back to village.
	 */
	public void backtovillage(){
		setX(RESTARTX);
		setY(RESTARTY);
		setHP(getMaxHP());
	}
	
	
	/**
	 * Attacks a monster in the world
	 * @param m The monster to be attacked.
	 */
	private void attack(Monster m) {
		
		//calculate a random amount of damage.
		Random rn = new Random();
		//Do random add 3 times to lower the variance of attack
		int damage = rn.nextInt(maxDamage/2 + 1) + rn.nextInt(maxDamage/2 + 1) + rn.nextInt(maxDamage/2 + 1);
		m.setHP(m.getHP() - damage);
		
		if(m instanceof PassiveMonster) {
			((PassiveMonster) m).setSafeconsidertimepassed(0);;
		}
		setCooldown(getTotalcooldowntime());
	}
	

    
}
