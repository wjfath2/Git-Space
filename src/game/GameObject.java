package game;

import java.awt.Rectangle;

public class GameObject {
	
	public double x;
	public double y;
	
	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int)x, (int)y, width,height);
	}

}
