package rendering;

import game.Main;

import java.awt.image.BufferedImage;


public class Textures {
	
	private SpriteSheet ss;
	public BufferedImage bullet;
	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage enemy;
	
	public Textures(Main game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	public void getTextures() { 
		player[0] = ss.grabImage(1,1,32,32);
		player[1] = ss.grabImage(1,2,32,32);
		player[2] = ss.grabImage(1,3,32,32);
		bullet = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(3, 1, 32, 32);
	}

}
