package game;

import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import rendering.Textures;
import classes.EntityB;

public class Enemy extends GameObject implements EntityB {
	
	
	
	private double velX = 0;
	private double velY = 1;
	
	private Controller c;

	
	private BufferedImage image;
	private Main game;
	private Textures t;
	private Bullet b;
	private Random r = new Random();
	private int speed = r.nextInt(3)+1;
	
	
	public Enemy(double x, double y, Textures t, Controller c, Main game, Bullet b) {
		super(x,y);
		this.t = t;
		this.b = b;
		this.c = c;
		this.game = game;
	}
	
	public void tick(){
		y += speed;
		
		if (y > game.height * game.scale) {
			speed = r.nextInt(3) +1;
			y = -10;
			x = r.nextInt(game.width * game.scale);
		}
		
		if(Physics.Collision(this, game.ea)) {
			c.removeEntity(this);
			
			game.setKilled(game.getKilled()+1);
			
			
			
		}
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(t.enemy, (int)x, (int)y, null);
		
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
