import java.util.Random;

import org.newdawn.slick.SlickException;


public abstract class AggressiveMonster extends Monster {
	
	private int maxDamage;
	
	/**
	 * Initialises an aggressive monster in the game.
	 * @param x The initial x coordinate of the monster.
	 * @param y The initial y coordinate of the monster.
	 * @param image_path The reference of the image to be used for this monster.
	 * @param name The name of the monster.
	 * @param maxHP The maximum health points that the monster can have.
	 * @param cooldown The number of milliseconds the monster has to wait for attacking again.
	 * @param maxDamage The maximum damage the monster can do in one attack.
	 * @throws SlickException
	 */
	public AggressiveMonster(double x, double y, String image_path, String name, int maxHP, int cooldown, int maxDamage)
	throws SlickException
	{
		super(x, y, image_path, name, maxHP, 0.25);
		setCooldown(0);
		this.setTotalcooldowntime(cooldown);
		this.maxDamage = maxDamage;
	}
	
	
	/**Get maximum damage of attacking of the monster. 
	 *@return The monster's maxDamage stat
	 */
	public int getMaxDamage() {
		return maxDamage;
	}
	/**Set the maximum damage of attacking of the monster. 
	 * @param maxDamage The monster's new maxDamage.
	 */
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	
	/**
	 * Update the monster's details in the world: moving, cooldown time, attacking and death.
	 * @param delta The number of milliseconds since the last frame.
	 * @param w The world of the game.
	 * @throws SlickException
	 */
	public void update(int delta, World w)
	throws SlickException
	{
		if(getHP() <= 0) {
			w.getDied().add(this);
			
		} else {
			Player player = w.getPlayer();
			
			setCooldown(getCooldown() - delta);
			double d = calculateDistance(player);
			if(d <= 150 && d > 50)
			{
				//chase player according to Algorithm 1
				double dx = player.getX() - this.getX();
				double dy = player.getY() - this.getY();
				move(w, dx/d, dy/d, delta);
			}
			else if(d <= 50 && (getCooldown() <= 0)){
				attack(player);
			}
		}
	}
	
	
	/**
	 * Attacks another unit in the world, always player in this game.
	 * @param u The unit to be attacked.
	 */
	private void attack(Unit u)
	{
		Random rn = new Random();
		//int damage = rn.nextInt(maxDamage + 1);
		int damage = rn.nextInt(maxDamage/2 + 1) + rn.nextInt(maxDamage/2 + 1) + rn.nextInt(maxDamage/2 + 1) + maxDamage - 3*(maxDamage/2);
		u.setHP(u.getHP() - damage);
		setCooldown(getTotalcooldowntime());
	}
}
	
		

