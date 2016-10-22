import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Villager extends Unit {


	private String dialogue;
	private int dialoguetimepassed;
	public final int dialogueremaintime = 4000;
	
	/**
	 * Creates a new villager within the game world.
	 * @param x The initial x coordinate of the villager.
	 * @param y The initial y coordinate of the villager.
	 * @param image_path The location of the image to be used for the villager.
	 * @param name The name of the villager.
	 * @param maxHP useless here.
	 * @param speed useless here.
	 */
	public Villager(double x, double y, String image_path, String name, int maxHP, double speed) 
	throws SlickException {
		super(x, y, image_path, name, 1, 0); 
		dialoguetimepassed = dialogueremaintime;
		dialogue = "";
	}
	
	
	
	public String getDialogue() {
		return dialogue;
	}
	
	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}
	
	
	public int getDialoguetimepassed() {
		return dialoguetimepassed;
	}
	
	public void setDialoguetimepassed(int dialogueTimer) {
		this.dialoguetimepassed = dialogueTimer;
	}
	
	
	
	public void update(int delta) {
		dialoguetimepassed += delta;
	}
	
	/**
	 * Interact with the world's player object.
	 * @param w The game world.
	 */
	public abstract void interact(World w) throws SlickException;
	
	/**
	 * If they are on screen, render the villager and their dialogue.
	 * @param g Graphics.
	 * @param cam camera.
	 */
	public void render(Graphics g, Camera cam) {
		super.render(g, cam);
		if(!cam.onScreen(this)){
			
    	}else if(dialoguetimepassed > dialogueremaintime){
    		
    	}else {
    		int textWidth = g.getFont().getWidth(dialogue);
    		int xDraw = (int)getX() - textWidth/2;
    		int yDraw = (int)getY() - 90;
    		g.setColor(new Color(1.0f, 1.0f, 1.0f));
    		g.drawString(dialogue, xDraw, yDraw);
    	}
	}
}
