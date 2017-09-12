package game;

import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

import rendering.Animation;
import rendering.Textures;

import classes.EntityA;

public class Player extends GameObject implements EntityA{
	
	
	private double velX = 0;
	private double velY = 0;
	

	Animation a;
	private Textures t;
	private Main game;
	private Controller c;
	
	
	
	private BufferedImage img = null;
	
	public Player(double x, double y, Textures t, Main game, Controller c) {
		super(x,y);
		this.game = game;
		this.t = t;
		this.c = c;
		
		a = new Animation(5, t.player[0], t.player[1], t.player[2]);
		
		
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (x <= 0) 
			x = 0;
		if (x >= 640 - 32)
			x = 640 - 32;
		if (y <= 0) 
			y = 0;
		if (y >= 480 - 32) 
			y = 480 - 32;
		
		a.runAnimation();
		
		if(Physics.Collision(this, game.eb)) {
			game.callStop();
		}
		
		
	}
	
	public void render(Graphics g) {
		a.drawAnimation(g, x, y, 0);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
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
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	
	

}
