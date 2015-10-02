package com.mazeit.ninja;

import java.applet.Applet;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;

import com.mazeit.ninja.framework.Animation;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private URL base;
	private Image currentSprite, playerImgRight, playerImgLeft, playerImgDuckLeft, playerImgDuckRight,
		playerImgHitLeft, playerImgHitRight;
	private com.mazeit.ninja.framework.Animation anim;
	private Ninja player;
	private MapLoader mapLoader = null;
	private Animation ninjaAnimLeft, ninjaAnimRight;	
	
	enum DIRECTION {
		LEFT, RIGHT;
	}
	
	@Override
	public void start() {
		initObjects();
		loadMap();
		startthread();
	}
	
	private void startthread() {
		Thread thread = new Thread(this);
		thread.start();
	}

	private void initObjects() {
		player = new Ninja();
	}

	private void loadMap() {
		mapLoader = new MapLoader(this);
		// Initialize Tiles
		try {
			mapLoader.loadMap("data/map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		setSize(800, 400);
		//setBackground(Color.GREEN);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Ninja game");
		
		initImages();
		
		anim = new Animation();
		anim.addFrame(playerImgRight, 1250);
		currentSprite = anim.getImage();
	}
	
	private void initImages() {
		playerImgRight = loadImage("ninja_right.png");
		playerImgLeft = loadImage("ninja_left.png");
		playerImgDuckLeft = loadImage("ninja_duck_left.png");
		playerImgDuckRight = loadImage("ninja_duck_right.png");
		playerImgHitLeft = loadImage("ninja_hit_left.png");
		playerImgHitRight = loadImage("ninja_hit_right.png");
		
		ninjaAnimRight = new Animation();
		ninjaAnimRight.addFrame(playerImgRight, 70);
		ninjaAnimRight.addFrame(loadImage("ninja_right_2.png"), 70);
		
		ninjaAnimLeft = new Animation();
		ninjaAnimLeft.addFrame(playerImgLeft, 70);
		ninjaAnimLeft.addFrame(loadImage("ninja_left_2.png"), 70);
	}

	public Image loadImage(String img) {
		URL base = null;
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getImage(base, "data/" + img);
	}

	@Override
	public void run() {
		while (true) {
			player.update();
			updateTiles();
			animate();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(currentSprite, player.getCenterX(),
				player.getCenterY(), this);
		paintTiles(g);
	}
	
	public void animate() {
		ninjaAnimRight.update(10);
		ninjaAnimLeft.update(10);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		this.keyPressed(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_LEFT:
				player.moveLeft();
				currentSprite = ninjaAnimLeft.getImage();
				break;
	
			case KeyEvent.VK_RIGHT:
				player.moveRight();
				currentSprite = ninjaAnimRight.getImage();
				break;
				
			case KeyEvent.VK_UP:
				if( player.getXDirection() == DIRECTION.LEFT) {
					currentSprite = playerImgLeft;
				}
				else {
					currentSprite = playerImgRight;
				}
				break;	
			
			case KeyEvent.VK_DOWN:
				if(player.getXDirection() == DIRECTION.LEFT) {
					currentSprite = playerImgDuckLeft;
				}
				else {
					currentSprite = playerImgDuckRight;
				}
				break;
			case KeyEvent.VK_CONTROL:
				if(player.getXDirection() == DIRECTION.LEFT) {
					currentSprite = playerImgHitLeft;
				}
				else {
					currentSprite = playerImgHitRight;
				}
				break;		
		}	
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				player.stop();
				break;
	
			case KeyEvent.VK_DOWN:
				player.stop();
				break;
	
			case KeyEvent.VK_LEFT:
				player.stop();
				break;
	
			case KeyEvent.VK_RIGHT:
				player.stop();
				break;
	
			case KeyEvent.VK_SPACE:
				player.stop();
				break;
		}
	}
	
	private void updateTiles() {
		for (int i = 0; i < mapLoader.getTilearray().size(); i++) {
			Tile t = (Tile) mapLoader.getTilearray().get(i);
			t.update();
		}
	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < mapLoader.getTilearray().size(); i++) {
			Tile t = (Tile) mapLoader.getTilearray().get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}
}
