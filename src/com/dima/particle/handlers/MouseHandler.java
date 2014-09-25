package com.dima.particle.handlers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener{
	
	private Point mousePoint = new Point(0,0);
	private boolean clicked = false;
	
	public void mouseDragged(MouseEvent e) {
		mousePoint = e.getPoint();
		clicked = true;
	}

	public void mouseMoved(MouseEvent e) {
		mousePoint = e.getPoint();		
		clicked = false;
	}
	
	public Point getPoint(){
		return mousePoint;
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public int getX(){
		return mousePoint.x;
	}
	
	public int getY(){
		return mousePoint.y;
	}
	
}
