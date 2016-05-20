package project.entities.creatures.player.internal;

import java.awt.image.BufferedImage;

public class Ability {
	
	private String name;
	public BufferedImage icon;
	
	
	public Ability(String abilityName, BufferedImage abilityIcon){
		this.name = abilityName;
		this.icon = abilityIcon;
		
	}
	
	
}
