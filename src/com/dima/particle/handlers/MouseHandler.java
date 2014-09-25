package com.dima.particle.handlers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener, MouseListener{
	
	private Point mousePoint = new Point(0,0);
	private boolean clicked = false;
	
	public void mouseDragged(MouseEvent e) {
		mousePoint = e.getPoint();
	}

	public void mouseMoved(MouseEvent e) {
		mousePoint = e.getPoint();		
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

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		clicked = true;
	}

	public void mouseReleased(MouseEvent e) {
		clicked = false;
		
	}
	
}
