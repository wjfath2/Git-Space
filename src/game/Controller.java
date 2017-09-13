package game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import rendering.Textures;

import classes.EntityA;
import classes.EntityB;

public class Controller {
	
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	private Random r = new Random();
	
	EntityA enta;
	EntityB entb;
	Textures t;
	private Bullet b;
	private Main game;
	
	
	public Controller(Textures t, Main game, Bullet b) {
		this.t = t;
		this.b = b;
		this.game = game;
		
	}
	
	public void createEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count; i++) {
			addEntity(new Enemy(r.nextInt(640), -10,t,this,game, b));
		}
	}
	
	public void removeBullet(int enemy_count, int enemy_killed) {
		removeEntity(b);
	}
	
	public void tick() {
		//A Class
		for(int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			enta.tick();
		}
		//B Class
		for(int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			entb.tick();
		}
		
	
		
		if (game.getKilled() == game.getCount()) {
			game.setCount(game.getCount() + 1); 
			createEnemy(game.getCount());
			game.setKilled(0);
		}
		
	}
	
	
	public void render(Graphics g) {
		//A Class
		for(int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			enta.render(g);
		}
		//B Class
		for(int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			entb.render(g);
		}
	}
	
	public void addEntity(EntityA block) {
		ea.add(block);
	}
	
	public void removeEntity(EntityA block) {
		ea.remove(block);
	}
	
	public void addEntity(EntityB block) {
		eb.add(block);
	}
	
	public void removeEntity(EntityB block) {
		eb.remove(block);
	}
	
	public LinkedList<EntityA> getEntityA() {
		return ea;
	}
	
	public LinkedList<EntityB> getEntityB() {
		return eb;
	}
	

}
