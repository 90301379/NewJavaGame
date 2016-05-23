package project.worlds;

import java.awt.Graphics;

import project.entities.EntityManager;
import project.entities.creatures.Follow;
import project.entities.creatures.Player;
import project.entities.statics.Rock;
import project.entities.statics.GreenTower;
import project.entities.statics.RedTower;
import project.entities.statics.Tree;
import project.tileGame.Handler;
import project.tiles.Tile;
import project.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	private GreenTower gt; 
	private Follow test; 

	
	int xTower1;
	int yTower1;

	
	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		
		// Temporary entity code!
//		entityManager.addEntity(new Tree(handler, 100, 100));
//		entityManager.addEntity(new Rock(handler, 100, 450));
	    gt = new GreenTower(handler, 384, 384);
		
		test = new Follow(handler, 170, 180);


		
		xTower1 = (int)gt.getX();
		yTower1 = (int)gt.getY();

     	entityManager.addEntity(gt);
     	entityManager.addEntity(test);

     	entityManager.addEntity(new RedTower(handler, 2688, 384));

		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
	}

	
	
	public void tick(){
		entityManager.tick();
		
		int x = (int)entityManager.getPlayer().getX();
		int y = (int)entityManager.getPlayer().getY();
		
		
		for(int i = 0; i < 64 ; i++){
			for(int j = 0; j < 64 ; j++){

		
		if(entityManager.getPlayer().collidesWith(i, j)){
			
		}
		
		
		}}
		
		test.followPlayer(entityManager.getPlayer());
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Entities
		entityManager.render(g);
		
		
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}








