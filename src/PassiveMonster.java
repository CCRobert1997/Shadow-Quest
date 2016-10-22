import org.newdawn.slick.SlickException;
//import java.util.ArrayList;
import java.util.Random;

public abstract class PassiveMonster extends Monster {
	
	private int movechangtimepassed;
	private int safeconsidertimepassed;
	private final int safeconsidertime = 5000;
	private final int movechangetime = 3000;
	
	private double dir_x;
	private double dir_y;

	/**
	 * Initialises a passive monster in the game.
	 * @param x The initial x coordinate of the monster.
	 * @param y The initial y coordinate of the monster.
	 * @param image_path The reference of the image to be used for the monster.
	 * @param name The name of the monster.
	 * @param maxHP The max HP that the monsters could have.
	 * @throws SlickException
	 */
	public PassiveMonster(double x, double y, String image_path, String name, int maxHP)//, int removeid)
	throws SlickException {
		super(x, y, image_path, name, maxHP, 0.2);//move at one fifth of a pixel per millisecond
		movechangtimepassed = movechangetime;
		safeconsidertimepassed = safeconsidertime;
	}
	
	
	/**
	 * Get the time passed after this monster change direction of movement
	 */
	public int getMovechangtimepassed() {
		return movechangtimepassed;
	}

	/**
	 * Set the time passed after this monster change direction of movement
	 * @param movechangtimepassed the value to change its movechangtimepassed.
	 */
	public void setMovechangtimepassed(int movechangtimepassed) {
		this.movechangtimepassed = movechangtimepassed;
	}

	/**
	 * Get the time passed after this monster attacked by player.
	 */
	public int getSafeconsidertimepassed() {
		return safeconsidertimepassed;
	}

	/**
	 * Set the time passed after this monster attacked by player.
	 * @param safeconsidertimepassed the time passed after this monster attacked by player.
	 */
	public void setSafeconsidertimepassed(int safeconsidertimepassed) {
		this.safeconsidertimepassed = safeconsidertimepassed;
	}

	

	/**
	 * Update the monster's location and activity within the world, including moving and managing timers.
	 * @param delta The number of milliseconds since the last frame.
	 * @param w The world of the game.
	 * @throws SlickException
	 */
	public void update(int delta, World w) 
	throws SlickException {
		
		if(getHP() <= 0) {
			w.getDied().add(this);
			
		} else {
			movechangtimepassed += delta;
			safeconsidertimepassed += delta;
				
			if(safeconsidertimepassed < safeconsidertime) {
				runaway(delta, w);
			}
			else{
				normalwalk(delta, w);
			}
		}
		
	}
	
	
	/**
	 * Evade the player after being attacked.
	 * @param delta The number of milliseconds since the last frame.
	 * @param w The world of the game.
	 * @throws SlickException
	 */
	private void runaway(int delta, World w) 
	throws SlickException {
		
		Player player = w.getPlayer();
		double d = calculateDistance(player);
		
		//run away according to algorithm 1
		double dx = this.getX() - player.getX();
		double dy = this.getY() - player.getY();
		dir_x = dx/d;
		dir_y = dy/d;
		
		move(w, dir_x, dir_y, delta);
	}
	
	
	/**
	 * Wander randomly around the world.
	 * @param delta The number of milliseconds since the last frame.
	 * @param w The world of the game.
	 * @throws SlickException
	 */
	private void normalwalk(int delta, World w) 
	throws SlickException {
		
		if(movechangtimepassed > movechangetime) {
			Random r = new Random();
			
			dir_x = r.nextInt(3)-1;
			dir_y = r.nextInt(3)-1;
			dir_x /= Math.sqrt(2);
			dir_y /= Math.sqrt(2);
			movechangtimepassed = 0;
		}
		move(w, dir_x, dir_y, delta);
	}
}
