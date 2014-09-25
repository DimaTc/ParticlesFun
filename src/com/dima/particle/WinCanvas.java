package com.dima.particle;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import com.dima.particle.entities.Particle;
import com.dima.particle.handlers.MouseHandler;

public class WinCanvas extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 480;
	public static final int HEIGHT= 360;
	public static final int SCALE = 2;
	
	public static final Dimension dim = new Dimension(WIDTH * SCALE , HEIGHT * SCALE);
	
	private LinkedList<Particle> particles= new LinkedList<Particle>();
	
	private int ups = 0;
	private long fps = 0;
	
	private boolean running;
	private Thread thread;
	
	private MouseHandler mh;
	
	public WinCanvas(){
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
		
		mh = new MouseHandler();
		
		addMouseMotionListener(mh);
		
	}
	
	
	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)return;
		running = false;
		try{
			thread.join();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		long lastTime = System.currentTimeMillis();
		long timer = System.currentTimeMillis();
		int updatesPerSecond = 60;
		double delta = 0;
		double ns = 1000000000 / updatesPerSecond;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				delta--;
				update();
				ups++;
				
			}
			draw();
			fps++;
			if(System.currentTimeMillis() - 1000 >= timer){
				timer += 1000;
				System.out.printf("fps - %d || ups - %d \n", fps, ups);
				fps = ups = 0;
			}
		}
	}
	
	public void update(){
		if(mh.isClicked()){
			particles.add(new Particle(mh.getPoint()));
		}
		for(Particle p : particles){
			if(p.isOutOfBounds()){
				particles.remove(p);
				return;
			}
			p.update();
		}
	}
	
	
	public void draw(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		//
		g.setColor(Color.black);
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(Color.green);
		
		for(Particle p : particles){
			if(p.isOutOfBounds()){
				particles.remove(p);
				return;
			}
			p.draw(g);
		}
		g.setColor(Color.white);
		g.drawString(Integer.toString(particles.size()), 20 , 20);
		g.fillOval(mh.getX() - 10, mh.getY() - 10, 20 , 20);
		
		
		//
		bs.show();
		g.dispose();
	}

	
}
