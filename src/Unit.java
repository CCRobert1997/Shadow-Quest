import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
public abstract class Unit extends GameObject{
	
	private int maxHP;
	private int HP;
	
	private String name;
	private int cooldown;
	private int totalcooldowntime;
	
	
	private Image img = null;
    private Image img_flipped = null;

    // In pixels
    private double x, y;
    private double width, height;
    protected boolean face_left = false;
    
    private double speed;
	
    //constant for name bar for better presentation
    private final int NameBarHeight = 20, BORDERFORNAMEBAR = 6, DELTAFORDRAWRECT = 70, DELTAFORDRAWSTRING = DELTAFORDRAWRECT - 2;
	/**
	 * Initialise a Unit.
	 * @param x The initial x coordinate of the unit.
	 * @param y The initial y coordinate of the unit.
	 * @param image_path The reference of the image to be used for the unit.
	 * @param name The name of the unit.
	 * @param maxHP The max HP that the unit could have.
	 * @param speed The speed in pixel of the unit.
	 * @throws SlickException
	 */
	public Unit(double x, double y, String image_path, String name, int maxHP, double speed)
	throws SlickException
	{
		super(x, y, image_path);
		this.img = new Image(image_path);
		img_flipped = img.getFlippedCopy(true, false);
		this.x = x;
	    this.y = y;
		this.name = name;
		this.maxHP = maxHP;
		this.HP = maxHP;
		this.speed = speed;
		this.width = img.getWidth();
        this.height = img.getHeight();
		
	}


	


	/**
	 * @return the max HP of the unit.
	 */
	public int getMaxHP() {
		return maxHP;
	}


	/**
	 * @param maxHP the max HP of the unit.
	 */
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}


	/**
	 * @return the HP left of the unit.
	 */
	public int getHP() {
		return HP;
	}


	/**
	 * @param Hp the HP left of the unit.
	 */
	public void setHP(int HP) {
		this.HP = HP;
	}


	/**
	 * @return the Name of the unit.
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the Name of the unit.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the time passed after last action of the unit.
	 */
	public int getCooldown() {
		return cooldown;
	}


	/**
	 * @param cooldown the time passed after last action of the unit.
	 */
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}


	/**
	 * @return the time need after last action of the unit to do this action again.
	 */
	public int getTotalcooldowntime() {
		return totalcooldowntime;
	}


	/**
	 * @param totalcooldowntime the time need after last action of the unit to do this action again.
	 */
	public void setTotalcooldowntime(int totalcooldowntime) {
		this.totalcooldowntime = totalcooldowntime;
	}


	/**
	 * @return image.
	 */
	public Image getImg() {
		return img;
	}


	/**
	 * @param img image.
	 */
	public void setImg(Image img) {
		this.img = img;
	}


	/**
	 * @return flipped image.
	 */
	public Image getImg_flipped() {
		return img_flipped;
	}


	/**
	 * @param img_flipped flipped image.
	 */
	public void setImg_flipped(Image img_flipped) {
		this.img_flipped = img_flipped;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public boolean isFace_left() {
		return face_left;
	}


	public void setFace_left(boolean face_left) {
		this.face_left = face_left;
	}
	
	
	
	/** Move the unit in a given direction.
     * Prevents the player from moving outside the map space, and also updates
     * the direction the player is facing.
     * @param world The world the player is on (to check blocking).
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
	public void move(World world, double dir_x, double dir_y, double delta)
	throws SlickException
	{
		// Update Unit facing based on X direction
        if (dir_x > 0)
            this.face_left = false;
        else if (dir_x < 0)
            this.face_left = true;
        
     // Move the unit by dir_x, dir_y, as a multiple of delta * speed
        double new_x = this.x + dir_x * delta * speed;
        double new_y = this.y + dir_y * delta * speed;
		
     // Move in x first

        double x_sign = Math.signum(dir_x);
        
        if(!world.terrainBlocks(new_x + x_sign * width / 2, this.y + height / 3) 
                && !world.terrainBlocks(new_x + x_sign * width / 2, this.y - height / 3)) {
            this.x = new_x;
        }
        
     // Then move in y

        double y_sign = Math.signum(dir_y);
        
        if(!world.terrainBlocks(this.x + width / 3, new_y + y_sign * height / 2) 
                && !world.terrainBlocks(this.x - width / 3, new_y + y_sign * height / 2)){
            this.y = new_y;
        }
		
	}
	/*
	 *  Draw the unit to the screen at the correct place only draw when unit's location in the screen.
	 *  @param g graphics
	 *  @param cam camera
     */
    public void render(Graphics g, Camera cam) {
    	if(!cam.onScreen(this)){
    		
    	} else if(this instanceof Player){
    		Image which_img;
            which_img = this.face_left ? this.img_flipped : this.img;
            which_img.drawCentered((int) x, (int) y);
            
    	} else {
    		Image which_img;
    		which_img = this.face_left ? this.img_flipped : this.img;
    		double health = HP/(double)maxHP;
        	double xinscreen = getX();
        	double yinscreen = getY(); 
        	which_img.drawCentered((float)xinscreen, (float)yinscreen);
        	Color textColor = new Color(1.0f, 1.0f, 1.0f, 1.0f); // White
        	Color barColor = new Color(0.8f, 0.0f, 0.0f, 0.8f); // Red
        	Color bgColor = new Color(1, 1, 1, 1); // Black
        	int textWidth = g.getFont().getWidth(name);
        	int NameBarWidth = textWidth + BORDERFORNAMEBAR;

        	g.setColor(bgColor);
        	g.fillRect((float)(xinscreen - NameBarWidth/2), (float)(yinscreen - DELTAFORDRAWRECT), NameBarWidth, NameBarHeight);
        	g.setColor(barColor);
        	g.fillRect((float)(xinscreen - NameBarWidth/2), (float)(yinscreen - DELTAFORDRAWRECT), (int)(health*NameBarWidth), NameBarHeight);
        	g.setColor(textColor);
        	g.drawString(name, (float)(xinscreen - g.getFont().getWidth(name)/2), (float)(yinscreen - DELTAFORDRAWSTRING));
        	
    	}
		
	}
}
