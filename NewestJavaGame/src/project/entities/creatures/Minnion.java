package project.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import project.entities.Entity;
import project.entities.statics.GreenTower;
import project.gfx.Animation;
import project.gfx.Assets;
import project.tileGame.Handler;

public class Minnion extends Creature {
	
	//Search Phase
	//Detect Phase
	//Attack Phase
	
	
	// Animations
	private Animation animDown, animUp, animLeft, animRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;

	private boolean alive = true;

	public Minnion(Handler handler, float x, float y) {
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

			if (this.speed <= 3.5F)
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
		// System.out.println("You lose");
	}

	private boolean moving = false;

	public void followPlayer(Player P, GreenTower T) {

		xMove = 0;
		yMove = 0;

		if (!moving && T.isInRangeWith(P)) {
			moving = true;
		}

		if (moving) {
			if (alive) {

				// getClosestPoint(P);
				// Above Below
				if (((this.getY() < P.getY() - 20) || (this.getY() > P.getY() + 50))
						// Left Right
						|| ((this.getX() < P.getX() - 20) || (this.getX() > P.getX() + 30))) {

					// Up
					if (this.getY() > P.getY()) {
						yMove = -speed;
						// System.out.println("Up");
					}
					// Down
					if (this.getY() < P.getY()) {
						yMove = speed;
						// System.out.println("Down");
					}
					// Right
					if (this.getX() > P.getX()) {
						xMove = -speed;
						// System.out.println("Left");
					}
					// Left
					if (this.getX() < P.getX()) {
						xMove = speed;
						// System.out.println("Right");
					}
				} else {

					if (alive) {
						P.damage(500);
					}

					alive = false;
					speed = 0.1f;
					this.setX(384);
					this.setY(384);
					alive = true;
					moving = false;

				}
			}
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

	public boolean isAlive() {
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
