package project.HUD;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import project.entities.creatures.player.internal.Ability;
import project.entities.creatures.player.internal.Stats;
import project.gfx.Assets;

public class playerHud {
	
	private Ability q;
	private Ability w;
	private Ability e;
	private Ability r;	
	
	public playerHud(Ability q, Ability w, Ability e, Ability r, Stats stats){
		
		this.q = q;
		this.w = w;
		this.e = e;
		this.r = r;
	}
	
	public void render(Graphics g){
		
		g.drawImage(q.icon, 10, 600, null);
		g.drawImage(w.icon, 354, 600, null);
		g.drawImage(e.icon, 482, 600, null);
		g.drawImage(r.icon, 546, 600, null);
		
		
	}
	
	
	
	
	
	
}
