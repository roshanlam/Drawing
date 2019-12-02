package com.roshan.main;

import java.awt.Color;
import java.io.Serializable;

public class Point implements Serializable {

	/* ATTRIBUTES */
	private int x;
	private int y;
	private Color color;
	private int size;
	private String shape;
	
	/* CONSTRUCTOR */
	public Point(int x, int y, Color color, int size, String shape) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.size = size;
		this.shape = shape;
	}
	
	/* METHODS */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
}
