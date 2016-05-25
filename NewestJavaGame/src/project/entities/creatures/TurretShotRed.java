package project.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import project.entities.Entity;
import project.gfx.Animation;
import project.gfx.Assets;
import project.tileGame.Handler;

public class TurretShotRed extends Creature {

	// Animations
	private Animation animDown, animUp, animLeft, animRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;

	private boolean alive = true;
	
	public TurretShotRed(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		speed = 0.1f;
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;

		// Animatons
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);

	}

	@Override
	public void tick() {
		
		if (alive) {
		// Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		// Movement
		move();
		this.speed = this.speed + 0.05F;
		// handler.getGameCamera().centerOnEntity(this);
		
		}
	
	}

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;

		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;

		if (handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		} else if (handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		} else if (handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else if (handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else {
			return;
		}

		attackTimer = 0;

		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}

	}

	@Override
	public void die() {
		System.out.println("You lose");
	}

	private boolean movingUp;
	private boolean movingDown;
	private boolean movingLeft;
	private boolean movingRight;

	public void followPlayer(Player P) {

		xMove = 0;
		yMove = 0;

		// getClosestPoint(P);
		// Above Below
		if (((this.getY() < P.getY() - 20) || (this.getY() > P.getY() + 20))
				// Left Right
				|| ((this.getX() < P.getX() - 20) || (this.getX() > P.getX() + 20))) {

			// Up
			if (this.getY() > P.getY()) {
				yMove = -speed;
				movingUp = true;
				System.out.println("Up");
			}
			// Down
			if (this.getY() < P.getY()) {
				yMove = speed;
				movingDown = true;
				System.out.println("Down");
			}
			// Right
			if (this.getX() > P.getX()) {
				xMove = -speed;
				movingLeft = true;
				System.out.println("Left");
			}
			// Left
			if (this.getX() < P.getX()) {
				xMove = speed;
				movingRight = true;
				System.out.println("Right");
			}
		} else {
			
			if(alive){
			P.stat.health = P.stat.health - 500;
			}
			alive = false;
		}

	}

	@Override
	public void render(Graphics g) {
		if (alive) {
			g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		}
		// g.setColor(Color.red);
		// g.fillRect((int) (x + bounds.x -
		// handler.getGameCamera().getxOffset()),
		// (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
		// bounds.width, bounds.height);
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	
	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else {
			return animDown.getCurrentFrame();
		}
	}

}
