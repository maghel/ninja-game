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
	private Image playerImg, currentSprite;
	private com.mazeit.ninja.framework.Animation anim;
	private Ninja player;
	private MapLoader mapLoader = null;
			
	@Override
	public void start() {
		player = new Ninja();
		
		loadMap();
				
		Thread thread = new Thread(this);
		thread.start();
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
		
		
		playerImg = loadImage("ninja.png");
		
		anim = new Animation();
		anim.addFrame(playerImg, 1250);
		
		currentSprite = anim.getImage();
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
		g.drawImage(playerImg, player.getCenterX(),
				player.getCenterY(), this);
		
		paintTiles(g);
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
				break;
	
			case KeyEvent.VK_RIGHT:
				player.moveRight();
				break;
				
			case KeyEvent.VK_UP:
				player.moveUp();
				break;	
			
			case KeyEvent.VK_DOWN:
				player.moveDown();
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
