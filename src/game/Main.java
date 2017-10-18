package game;
import java.awt.Canvas;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import classes.EntityA;
import classes.EntityB;

import rendering.BufferedImageLoader;
import rendering.Textures;

public class Main extends Canvas implements Runnable {
	
	public static final int width = 320;
	public static final int height = width/12*9;
	public static final int scale = 2;
	public final String title = "Space Game";
	
	private int enemy_count = 10;
	private int enemy_killed = 0;
	
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
	private Player p;
	private Controller c;
	private Textures t;
	private Enemy e;
	private Bullet b;
	private Menu menu;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	public static enum STATE {
		Menu,
		Game
	};
	
	public static STATE State = STATE.Menu;
	
	private boolean isShooting = false;

	
	

	
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			
			spriteSheet = loader.loadImage("/Sprite Sheet.png");
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput());
		t = new Textures(this);
		menu = new Menu();
		p = new Player(200,400,t, this, c);
		c = new Controller(t, this, b);
		
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		
		c.createEnemy(enemy_count);
		
		
		
		
		
	}
	
	private synchronized void start(){
		if (running) {
			return;
		}else{
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	private synchronized void stop() {
		if (!running) {
			return;
		}else{
			try{
				thread.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(1);
		}
			
		
	}
	
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "Ticks, Fps" + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if (State == STATE.Game) {
			p.tick();
			c.tick();
			System.out.println("Enemy" + enemy_killed);
		}
	
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		if(State == STATE.Game) {
		
			p.render(g);
			c.render(g);
		}else if(State == STATE.Menu) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(State == STATE.Game) {
			
			if(key == KeyEvent.VK_W) {
			p.setVelY(-5);
			} else if (key == KeyEvent.VK_S) {
			p.setVelY(5);
			}else if (key == KeyEvent.VK_A) {
			p.setVelX(-5);
			}else if (key == KeyEvent.VK_D) {
			p.setVelX(5);
			}else if (key == KeyEvent.VK_SPACE && isShooting == false) {
			isShooting = true;
			c.addEntity(new Bullet(p.getX(), p.getY(), t, this, c));
			}
	}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(State == STATE.Game) {
		
			if(key == KeyEvent.VK_W) {
			p.setVelY(0);
			}else if(key == KeyEvent.VK_S) {
			p.setVelY(0);
			}else if(key == KeyEvent.VK_A) {
			p.setVelX(0);
			}else if(key == KeyEvent.VK_D) {
			p.setVelX(0);
			}else if(key == KeyEvent.VK_SPACE) {
			isShooting = false;
			}
		}
	}
	
	public static void main(String args[]) {
		Main game = new Main();
		
		game.setPreferredSize(new Dimension(width * scale, height * scale));
		game.setMaximumSize(new Dimension(width * scale, height * scale));
		game.setMinimumSize(new Dimension(width * scale, height * scale));
		
		
		JFrame frame = new JFrame(game.title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	public int getCount() {
		return enemy_count;
	}
	
	public int getKilled() {
		return enemy_killed;
	}
	
	public void setCount(int enemy_count) {
		this.enemy_count = enemy_count;
	}
	
	public void setKilled(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	
	public void callStop() {
		stop();
		
	}
	
	
	
	
	


}
