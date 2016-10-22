import org.newdawn.slick.SlickException;


public class Draelic extends AggressiveMonster{
	
	/**
	 * Creates the Boss in the game.
	 * @param x The initial x coordinate of the draelic.
	 * @param y The initial y coordinate of the draelic.
	 * @throws SlickException
	 */
	public Draelic(double x, double y)
	throws SlickException
	{
		super(x, y, "assets/units/necromancer.png", "Draelic", 140, 400, 30);
		this.setImg(this.getImg().getScaledCopy(2));
		this.setImg_flipped(this.getImg().getFlippedCopy(true, false));
	}

}
