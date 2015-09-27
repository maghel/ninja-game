package com.mazeit.ninja;

public class Ninja {

	private static final int MOVE_SPEED = 2;
	private int centerX = 10;
	private int centerY = 10;
	
	private int speedX = 0;
	private int speedY = 0;
	
	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}


	public void moveRight() {
		speedX = MOVE_SPEED;
		speedY = 0;
	}

	public void moveLeft() {
		speedX = -MOVE_SPEED;
		speedY = 0;
	}
	
	public void moveUp() {
		speedY = -MOVE_SPEED;
		speedX = 0;
	}

	public void moveDown() {
		speedY = MOVE_SPEED;
		speedX = 0;
	}
	
	public void update() {
		centerX += speedX;
		centerY += speedY;
	}

	public void stop() {
		speedY = 0;
		speedX = 0;
	}
}
