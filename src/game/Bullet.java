package game;

import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import java.awt.Rectangle;

import classes.EntityA;
import java.awt.image.BufferedImage;

import rendering.Textures;

public class Bullet extends GameObject implements EntityA {
	
	
	
	private Textures t;
	private Main game;
	private Controller c;
	private int killed = 0;
	
	public Bullet(double x, double y, Textures t, Main game, Controller c) {
		super(x,y);
		this.c = c;
		this.game = game;
		this.t = t;
		
		
	}
	

	public void tick() {
		y -= 10;
		
	
			
			
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(t.bullet, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32,32);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Bullet getBullet() {
		return this;
	}
	
	public void timer() {
		long currentTime = System.currentTimeMillis();
		
		if(currentTime == currentTime + 1000) {
			c.removeEntity(this);
		}
		
	}
}
