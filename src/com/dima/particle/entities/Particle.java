package com.dima.particle.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import com.dima.particle.WinCanvas;

public class Particle {
	
	private Point startPosition = new Point(0,0);
	private Random rand = new Random(new Random().nextInt());
	private int dx;
	private int dy;
	private int timeAlive = 0;
	private int color;
	
	private int size = 30;
	
	private static final int RED 		= 0;
	private static final int BLUE 	 	= 1;
	private static final int GREEN  	= 2;
	private static final int YELLOW 	= 3;
	private static final int ORANGE 	= 4;
	private static final int CYAN	 	= 5;
	
	public Particle(Point startPosition){
		this.startPosition = startPosition;
		setRandomSpeed();
		setRandomColor();
	}

	public Particle(int x, int y){
		this.startPosition = new Point(x, y);
		setRandomSpeed();
		setRandomColor();
	}
	
	private void setRandomSpeed(){
		dx = rand.nextInt(20) - 10;
		if(dx == 0)
			switch(rand.nextInt(1)){
			case 0:
				dx = 1;
				break;
			case 1:
				dx = -1;
				break;
			}
		dy = rand.nextInt(20) - 10;
		if(dy == 0)
			switch(rand.nextInt(1)){
			case 0:
				dy = 1;
				break;
			case 1:
				dy = -1;
				break;
			}
	}
		
	public void setRandomColor(){
		color = rand.nextInt(6);
	}
	
	public boolean isOutOfBounds(){
		if(startPosition.x + size < 0 || startPosition.x - size > WinCanvas.dim.width
				|| startPosition.y + size < 0 || startPosition.y - size > WinCanvas.dim.height)
			return true;
		else
			return false;
	}
	
	public void update(){
		timeAlive++;
		if(timeAlive > 5){
			timeAlive = 0;
			if(dx > 1)
				dx--;
			if(dy > 1)
				dy--;
		}
		startPosition.x += dx;
		startPosition.y += dy;
	}
	
	public void draw(Graphics2D g){
		switch(color){
		case RED:
			g.setColor(Color.red);
			break;
		case BLUE:
			g.setColor(Color.blue);
			break;
		case GREEN:
			g.setColor(Color.green);
			break;
		case YELLOW:
			g.setColor(Color.yellow);
			break;
		case ORANGE:
			g.setColor(Color.orange);
			break;
		case CYAN:
			g.setColor(Color.cyan);
			break;
			default:
				g.setColor(Color.white);
				break;
		}
		g.fillOval(startPosition.x, startPosition.y, size, size);
	}
}
