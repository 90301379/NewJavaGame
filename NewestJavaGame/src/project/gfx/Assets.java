package project.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage dirt, grass, stone, tree, rock;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] tower;

	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet RotisserieL1 = new SpriteSheet(ImageLoader.loadImage("/textures/RM-WalkL.png"));
		SpriteSheet RotisserieL2 = new SpriteSheet(ImageLoader.loadImage("/textures/RM-WalkL2.png"));
		SpriteSheet RotisserieR1 = new SpriteSheet(ImageLoader.loadImage("/textures/RM-WalkR.png"));
		SpriteSheet RotisserieR2 = new SpriteSheet(ImageLoader.loadImage("/textures/RM-WalkR2.png"));
		SpriteSheet RotisserieIdle1 = new SpriteSheet(ImageLoader.loadImage("/textures/RM-IR.png"));
		SpriteSheet RotisserieIdle2 = new SpriteSheet(ImageLoader.loadImage("/textures/RM-IR2.png"));

		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_down[0] = RotisserieIdle1.crop(0, 0, width*2, height*2);
		player_down[1] = RotisserieIdle2.crop(0, 0, width*2, height*2);
		player_up[0] = RotisserieIdle1.crop(0, 0, width*2, height*2);
		player_up[1] = RotisserieIdle2.crop(0, 0, width*2, height*2);
		player_right[0] = RotisserieR1.crop(0, 0, width*2, height*2);
		player_right[1] = RotisserieR2.crop(0, 0, width*2, height*2);
		player_left[0] = RotisserieL1.crop(0, 0, width*2, height*2);
		player_left[1] = RotisserieL2.crop(0, 0, width*2, height*2);
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
		
		tower = new BufferedImage[8];

		tower[0] = sheet.crop(0, 0, width * 2, height * 3);
		tower[1] = sheet.crop(0, height * 4, width * 2, height * 3);
		tower[2] = sheet.crop(0, height * 4, width * 2, height * 3);
		tower[3] = sheet.crop(0, height * 4, width * 2, height * 3);
		tower[4] = sheet.crop(0, height * 4, width * 2, height * 3);
		tower[5] = sheet.crop(0, height * 4, width * 2, height * 3);
		tower[6] = sheet.crop(0, height * 4, width * 2, height * 3);
		tower[7] = sheet.crop(0, height * 4, width * 2, height * 3);

		
		
		
	}
	
}

