/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Sample Solution
 * Author: Matt Giuca <mgiuca>
 */

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	
    private static final int PLAYER_START_X = 756, PLAYER_START_Y = 684, PLAYERHP = 100, PLAYERDAMAGE = 26, PLAYERCOOLDOWN = 600;
    //private static final int PLAYER_START_X = 1976, PLAYER_START_Y = 402, PLAYERHP = 10000, PLAYERDAMAGE = 26, PLAYERCOOLDOWN = 600;
    private Player player = null;
    private TiledMap map = null;
    private Camera camera = null;

    private Elvira healer;
	private PrinceAldric prince;
	private Garth garth;
	//collection of monsters alive
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	//collection of all items.
    private ArrayList<Item> items = new ArrayList<Item>();
    //collection of monsters dying this frame
    private ArrayList<Monster> died = new ArrayList<Monster>();
    
    private Image panel = new Image("assets/panel.png") ;
    
    

	
    
    /** 
     * @return Died monsters. */
    public ArrayList<Monster> getDied() {
		return died;
	}
    /** @param died Died monsters. */
	public void setDied(ArrayList<Monster> died) {
		this.died = died;
	}
	/** @return Alive monsters. */
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	/** @param monsters Alive monsters. */
	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}

	/** @return Map width, in pixels. */
    private int getMapWidth()
    {
        return map.getWidth() * getTileWidth();
    }

    /** @return Map height, in pixels. */
    private int getMapHeight()
    {
        return map.getHeight() * getTileHeight();
    }

    /** @return Tile width, in pixels. */
    public int getTileWidth()
    {
        return map.getTileWidth();
    }

    /** @return Tile height, in pixels. */
    private int getTileHeight()
    {
        return map.getTileHeight();
    }
    
    
    /** @return Protagonist */
    public Player getPlayer() {
		return player;
	}
    /** @param player Protagonist */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/** Create a new World object.
	 * @throws SlickException 
	 * */
    public World()
    throws SlickException
    {
        map = new TiledMap(RPG.ASSETS_PATH + "/map.tmx", RPG.ASSETS_PATH);
        player = new Player(RPG.ASSETS_PATH + "/units/player.png", PLAYER_START_X, PLAYER_START_Y, PLAYERHP, PLAYERDAMAGE, PLAYERCOOLDOWN);
        camera = new Camera(player, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
        
        initialiseunits(monsters);
        
        items.add(new Tome(546, 6707)); //items.get(0)
    	items.add(new Sword(4791, 1253)); //items.get(1)
    	items.add(new Amulet(965, 3563)); //items.get(2)
    	items.add(new Elixir(1976, 402)); //items.get(3)
    }
    /** @return All items.*/
    public ArrayList<Item> getItems() {
		return items;
	}
    /** @param items All items.*/
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	/**
	 * Overload update
	 * Can be used only elixir has been found
	 * @param mouse_x mouseclick x
	 * @param mouse_y mouseclick y
	 */
	
	public void update(int mouse_x, int mouse_y){
		if (player.getInventory().contains(getItems().get(3))){
			if ((mouse_x <= 155)  && (mouse_y >= RPG.SCREEN_HEIGHT - RPG.panelheight - 20) && (mouse_y <= RPG.SCREEN_HEIGHT - RPG.panelheight + 10)) {
	    		player.backtovillage();
	    	}
		}
	}
	

	/** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     * @param attack whether attack.
     * @param talk whether talk.
     * @throws SlickException
     */
    public void update(int dir_x, int dir_y, int delta, boolean attack, boolean talk)
    throws SlickException
    {
        
        player.update(dir_x, dir_y, delta, this, attack, talk);
        camera.update();
        healer.update(delta);
        prince.update(delta);
        garth.update(delta);
        
        for(Monster m : monsters){
        	
    		m.update(delta, this);
        }
        for(Monster i : died)
    		monsters.remove(i);
    	died.clear();
    }

    public Elvira getHealer() {
		return healer;
	}

	public void setHealer(Elvira healer) {
		this.healer = healer;
	}

	public PrinceAldric getPrince() {
		return prince;
	}

	public void setPrince(PrinceAldric prince) {
		this.prince = prince;
	}

	public Garth getGarth() {
		return garth;
	}

	public void setGarth(Garth garth) {
		this.garth = garth;
	}

	/** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     * @throws SlickException 
     */
    public void render(Graphics g)
    throws SlickException
    {
        // Render the relevant section of the map
        int x = -(camera.getMinX() % getTileWidth());
        int y = -(camera.getMinY() % getTileHeight());
        int sx = camera.getMinX() / getTileWidth();
        int sy = camera.getMinY()/ getTileHeight();
        int w = (camera.getMaxX() / getTileWidth()) - (camera.getMinX() / getTileWidth()) + 1;
        int h = (camera.getMaxY() / getTileHeight()) - (camera.getMinY() / getTileHeight()) + 1;
        map.render(x, y, sx, sy, w, h);
        
        renderPanel(g);
        // Translate the Graphics object
        g.translate(-camera.getMinX(), -camera.getMinY());

        
        // Render the player
        player.render(g, camera);
        healer.render(g, camera);
        prince.render(g, camera);
        garth.render(g, camera);
        for(Item item : items){
    		item.render(g, camera);
        }
        for(Monster m : monsters) {
    		m.render(g,  camera);
        }
    }

    
    /** Renders the player's status panel.
     * @param g The current Slick graphics context.
     */
    public void renderPanel(Graphics g)
    {
        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, RPG.SCREEN_HEIGHT - RPG.panelheight);

        // Display the player's health
        text_x = 15;
        text_y = RPG.SCREEN_HEIGHT - RPG.panelheight + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        //text = "??/??";                                 // TODO: HP / Max-HP
        text = player.getHP() + "/" + player.getMaxHP(); 
        
        bar_x = 90;
        bar_y = RPG.SCREEN_HEIGHT - RPG.panelheight + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = (float)(player.getHP()/((double)player.getMaxHP()));//0.75f;                         // TODO: HP / Max-HP
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        //text = "??";                                    // TODO: Damage
        text = Integer.toString(player.getMaxDamage());   
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        //text = "??";                                    // TODO: Cooldown
        text = Integer.toString(player.getTotalcooldowntime());   
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = RPG.SCREEN_HEIGHT - RPG.panelheight + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);

        inv_x = 490;
        inv_y = RPG.SCREEN_HEIGHT - RPG.panelheight
            + ((RPG.panelheight - 72) / 2);
        for(Item item : player.getInventory())
        {
            // Render the item to (inv_x, inv_y)
        	item.getImg().draw(inv_x, inv_y);
        	inv_x += 72;
        	if (item instanceof Elixir){
        		g.setColor(LABEL);
        		bar_x = 15;
        		int backtovillagestring_y = bar_y - 30;
        		g.drawString("Back to village", bar_x, backtovillagestring_y);
        	}
        }
    }

    /** Determines whether a particular map coordinate blocks movement.
     * @param x Map x coordinate (in pixels).
     * @param y Map y coordinate (in pixels).
     * @return true if the coordinate blocks movement.
     */
    public boolean terrainBlocks(double x, double y)
    {
        // Check we are within the bounds of the map
        if (x < 0 || y < 0 || x > getMapWidth() || y > getMapHeight()) {
            return true;
        }
        
        // Check the tile properties
        int tile_x = (int) x / getTileWidth();
        int tile_y = (int) y / getTileHeight();
        int tileid = map.getTileId(tile_x, tile_y, 0);
        String block = map.getTileProperty(tileid, "block", "0");
        return !block.equals("0");
    }
    
    /**
	 * Initialises all units in the game.
	 * @param monsters The array list in which the monsters are to be stored.
	 * @throws SlickException
	 */
	private void initialiseunits(ArrayList<Monster> monsters)
	throws SlickException
	{
	    
		prince = new PrinceAldric(467, 769, "Prince Aldric");
		healer = new Elvira(738, 549, "Elvira");
		garth = new Garth(756, 870, "Garth");
		
		
		monsters.add(new GiantBat(1431, 864));
		monsters.add(new GiantBat(1154, 1321));
		monsters.add(new GiantBat(807, 2315));
		monsters.add(new GiantBat(833, 2657));
		monsters.add(new GiantBat(1090, 3200));
		monsters.add(new GiantBat(676, 3187));
		monsters.add(new GiantBat(836, 3966));
		monsters.add(new GiantBat(653, 4367));
		monsters.add(new GiantBat(1343, 2731));
		monsters.add(new GiantBat(1835, 3017));
		monsters.add(new GiantBat(1657, 3954));
		monsters.add(new GiantBat(1054, 5337));
		monsters.add(new GiantBat(801, 5921));
		monsters.add(new GiantBat(560, 6682));
		monsters.add(new GiantBat(1275, 5696));
		monsters.add(new GiantBat(1671, 5991));
		monsters.add(new GiantBat(2248, 6298));
		monsters.add(new GiantBat(2952, 6373));
		monsters.add(new GiantBat(3864, 6695));
		monsters.add(new GiantBat(4554, 6443));
		monsters.add(new GiantBat(4683, 6228));
		monsters.add(new GiantBat(5312, 6606));
		monsters.add(new GiantBat(5484, 5946));
		monsters.add(new GiantBat(6371, 5634));
		monsters.add(new GiantBat(5473, 3544));
		monsters.add(new GiantBat(5944, 3339));
		monsters.add(new GiantBat(6301, 3414));
		monsters.add(new GiantBat(6388, 1994));
		monsters.add(new GiantBat(6410, 1584));
		monsters.add(new GiantBat(5314, 274));

		monsters.add(new Zombie(681, 3201));
		monsters.add(new Zombie(691, 4360));
		monsters.add(new Zombie(2166 ,2650));
		monsters.add(new Zombie(2122, 2725));
		monsters.add(new Zombie(2284, 2962));
		monsters.add(new Zombie(2072, 4515));
		monsters.add(new Zombie(2006, 4568));
		monsters.add(new Zombie(2385, 4629));
		monsters.add(new Zombie(2446, 4590));
		monsters.add(new Zombie(2517, 4532));
		monsters.add(new Zombie(4157, 6730));
		monsters.add(new Zombie(4247, 6620));
		monsters.add(new Zombie(4137, 6519));
		monsters.add(new Zombie(4234, 6449));
		monsters.add(new Zombie(5879, 4994));
		monsters.add(new Zombie(5954, 4928));
		monsters.add(new Zombie(6016, 4866));
		monsters.add(new Zombie(5860, 4277));
		monsters.add(new Zombie(5772, 4277));
		monsters.add(new Zombie(5715, 4277));
		monsters.add(new Zombie(5653, 4277));
		monsters.add(new Zombie(5787, 797));
		monsters.add(new Zombie(5668, 720));
		monsters.add(new Zombie(5813, 454));
		monsters.add(new Zombie(5236, 917));
		monsters.add(new Zombie(5048, 1062));
		monsters.add(new Zombie(4845, 996));
		monsters.add(new Zombie(4496, 575));
		monsters.add(new Zombie(3457, 273));
		monsters.add(new Zombie(3506, 779));
		monsters.add(new Zombie(3624, 1192));
		monsters.add(new Zombie(2931, 1396));
		monsters.add(new Zombie(2715, 1326));
		monsters.add(new Zombie(2442, 1374));
		monsters.add(new Zombie(2579, 1159));
		monsters.add(new Zombie(2799, 1269));
		monsters.add(new Zombie(2768, 739));
		monsters.add(new Zombie(2099, 956));
		
		monsters.add(new Bandit(1889,2581));
		monsters.add(new Bandit(4502, 6283));
		monsters.add(new Bandit(5248, 6581));
		monsters.add(new Bandit(5345, 6678));
		monsters.add(new Bandit(5940, 3412));
		monsters.add(new Bandit(6335, 3459));
		monsters.add(new Bandit(6669, 260));
		monsters.add(new Bandit(6598, 339));
		monsters.add(new Bandit(6598, 528));
		monsters.add(new Bandit(6435, 528));
		monsters.add(new Bandit(6435, 678));
		monsters.add(new Bandit(5076, 1082));
		monsters.add(new Bandit(5191, 1187));
		monsters.add(new Bandit(4940, 1175));
		monsters.add(new Bandit(4760, 1039));
		monsters.add(new Bandit(4883, 889));
		monsters.add(new Bandit(4427, 553));
		monsters.add(new Bandit(3559, 162));
		monsters.add(new Bandit(3779, 1553));
		monsters.add(new Bandit(3573, 1553));
		monsters.add(new Bandit(3534, 2464));
		monsters.add(new Bandit(3635, 2464));
		monsters.add(new Bandit(3402, 2861));
		monsters.add(new Bandit(3151, 2857));
		monsters.add(new Bandit(3005, 2997));
		monsters.add(new Bandit(2763, 2263));
		monsters.add(new Bandit(2648, 2263));
		monsters.add(new Bandit(2621, 1337));
		monsters.add(new Bandit(2907, 1270));
		monsters.add(new Bandit(2331, 598));
		monsters.add(new Bandit(2987, 394));
		monsters.add(new Bandit(1979, 394));
		monsters.add(new Bandit(2045, 693));
		monsters.add(new Bandit(2069, 1028));
		
		monsters.add(new Skeleton(1255, 2924));
		monsters.add(new Skeleton(2545, 4708));
		monsters.add(new Skeleton(4189, 6585));
		monsters.add(new Skeleton(5720, 622));
		monsters.add(new Skeleton(5649, 767));
		monsters.add(new Skeleton(5291, 312));
		monsters.add(new Skeleton(5256, 852));
		monsters.add(new Skeleton(4970, 976));
		monsters.add(new Skeleton(4648, 401));
		monsters.add(new Skeleton(3628, 1181));
		monsters.add(new Skeleton(3771, 1181));
		monsters.add(new Skeleton(3182, 2892));
		monsters.add(new Skeleton(3116, 3033));
		monsters.add(new Skeleton(2803, 2901));
		monsters.add(new Skeleton(2850, 2426));
		monsters.add(new Skeleton(2005, 1524));
		monsters.add(new Skeleton(2132, 1427));
		monsters.add(new Skeleton(2242, 1343));
		monsters.add(new Skeleton(2428, 771));
		monsters.add(new Skeleton(2427, 907));
		monsters.add(new Skeleton(2770, 613));
		monsters.add(new Skeleton(2915, 477));
		monsters.add(new Skeleton(1970, 553));
		monsters.add(new Skeleton(2143, 1048));
		
		monsters.add(new Draelic(2069, 510));
	}
}




