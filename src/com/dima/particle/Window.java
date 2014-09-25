package com.dima.particle;

import javax.swing.JFrame;

public class Window {
	public static void main(String[] args){
		JFrame frame = new JFrame("Particles!");
		WinCanvas canvas = new WinCanvas();
		frame.add(canvas);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		canvas.start();
	}
}
