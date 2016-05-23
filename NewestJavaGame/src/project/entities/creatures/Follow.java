package project.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import project.entities.Entity;
import project.gfx.Animation;
import project.gfx.Assets;
import project.tileGame.Handler;

public class Follow extends Creature {
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	public Follow(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		speed = 1.0f;
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
		
		//Animatons
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		//Movement
		move();
		//handler.getGameCamera().centerOnEntity(this);

	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft){
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().aRight){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else{
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				e.hurt(1);
				return;
			}
		}
		
	}
	
	@Override
	public void die(){
		System.out.println("You lose");
	}
	
	public boolean collidesWith(int x, int y){
		
		if((int)this.getX() == x && (int)this.getY() == y){
			System.out.println("Colides with x ansdasdadd y | "+x+","+y);
			System.out.println("Colides with x and y | "+this.getX()+","+this.getY());
			return true;
		}
		return false;

	
	}
	
	private boolean movingUp;
	private boolean movingDown;
	private boolean movingLeft;
	private boolean movingRight;
	
	
	public void followPlayer(Player P){
		xMove = 0;
		yMove = 0;
		
		//Up
		if(this.getY() > P.getY()+20){
			yMove = -speed;
			movingUp = true;
			System.out.println("Up");
		}else{
			movingUp = false;
		}
		//Down
		if(this.getY() < P.getY()+20){
			yMove = speed;
			movingDown = true;
			System.out.println("Down");
		}else{
			movingDown = false;
		}
		//Right
		if(this.getX() > P.getX()+20){
			xMove = -speed;
			movingLeft = true;
			System.out.println("Left");
		}else{
			movingLeft = false;
		}
			
		//Left
		if(this.getX() < P.getX()+20){
			xMove = speed;
			movingRight = true;
			System.out.println("Right");
		}else{
			movingRight = false;
		}
		
		if (!movingLeft && !movingRight && !movingUp && !movingDown) {
			System.out.println("plz work");
		}
		
		
		if(handler.getMouseManager().isLeftPressed()){
			xMove = speed*2;
		}
		
	}
	
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if(yMove < 0){
			return animUp.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}
	}

}
