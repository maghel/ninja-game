package com.mazeit.ninja;

import com.mazeit.ninja.StartingClass.DIRECTION;

public class Ninja {

	private static final int MOVE_SPEED = 2;
	private int centerX = 100;
	private int centerY = 270;
	
	private int speedX = 0;
	private int speedY = 0;
	
	private DIRECTION xDirection;
	
	public DIRECTION getXDirection() {
		return xDirection;
	}

	public void setDirection(DIRECTION direction) {
		this.xDirection = direction;
	}

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
		xDirection = DIRECTION.RIGHT;
		speedX = MOVE_SPEED;
		speedY = 0;
	}

	public void moveLeft() {
		xDirection = DIRECTION.LEFT;
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
