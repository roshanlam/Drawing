package com.roshan.main;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	/* CONSTRUCTOR */
	public MainFrame(Model model) {
		super("Drawing - " + model.getName() + " : " + model.getWidth() + "x" + model.getHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
