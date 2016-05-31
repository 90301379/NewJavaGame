package project.entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import project.entities.creatures.Player;
import project.gfx.Animation;
import project.gfx.Assets;
import project.tileGame.Handler;
import project.tiles.Tile;

public class GreenTower extends StaticEntity {

	private Animation towerAnimation;

	public GreenTower(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 3);

		bounds.x = 0;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width;
		bounds.height = (int) (height - height / 1.5f);

		towerAnimation = new Animation(500, Assets.tower);

	}

	@Override
	public void tick() {

		towerAnimation.tick();

	}

	@Override
	public void die() {

	}

	@Override
	public void render(Graphics g) {
		BufferedImage loadImg = getCurrentAnimationFrame();
		g.drawImage(loadImg, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

	}

	private BufferedImage getCurrentAnimationFrame() {
		return towerAnimation.getCurrentFrame();
	}

	public boolean isInRangeWith(Player p) {
		if ((p.getX() > this.getX() - 256 && p.getX() < this.getX() + 320)
				&& (p.getY() > this.getY() - 256 && p.getY() < this.getY() + 320)) {
			return true;
		}

		return false;
	}

}
