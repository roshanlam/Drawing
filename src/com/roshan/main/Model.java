package com.roshan.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	/* ATTRIBUTES */
	private ArrayList<ChangeListener> views;
	private ArrayList<be.christopher.main.Point> pointsList;
	private int penSize;
	private Color color;
	private ArrayList<String> shapesList;
	private String shape;
	private Color backgroundColor;
	private int x;
	private int y;
	private boolean isReset;
	private int width;
	private int height;
	private String name;
	private JPanel drawing;

	/* CONSTRUCTOR */
	public Model(String name, int width, int height) {
		views = new ArrayList<ChangeListener>();
		pointsList = new ArrayList<be.christopher.main.Point>();
		penSize = 5;
		color = Color.BLACK;
		shapesList = new ArrayList<String>(Arrays.asList("Circle", "Rectangle"));
		shape = shapesList.get(0);
		backgroundColor = Color.WHITE;
		x = -1;
		y = -1;
		isReset = true;
		this.name = name;
		this.width = width;
		this.height = height;
	}
	
	
	/* METHODS */
	public void addView(ChangeListener view) {
		if(view == null)
			throw new NullPointerException("La vue spécifiée n'existe pas !");
		else if(!this.views.contains(view))
			this.views.add(view);
	}
	
	public void removeView(ChangeListener view) {
		if(view == null)
			throw new NullPointerException("La vue spécifiée n'existe pas !");
		else if(this.views.contains(view))
			this.views.remove(view);
	}
	
	public void processEvent(ChangeEvent e) {
		for(ChangeListener view : views)
			view.stateChanged(e);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		processEvent(new ChangeEvent(this));
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		processEvent(new ChangeEvent(this));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		processEvent(new ChangeEvent(this));
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		processEvent(new ChangeEvent(this));
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
		processEvent(new ChangeEvent(this));
	}

	public int getPenSize() {
		return penSize;
	}

	public void setPenSize(int penSize) {
		if(penSize >= 2 && penSize <= 100)
			this.penSize = penSize;
		processEvent(new ChangeEvent(this));
	}

	public boolean getIsReset() {
		return isReset;
	}

	public void setIsReset(boolean isReset) {
		this.isReset = isReset;
	}

	public ArrayList<be.christopher.main.Point> getPointsList() {
		return pointsList;
	}

	public  ArrayList<String> getShapesList() {
		return shapesList;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void saveProject(ArrayList<be.christopher.main.Point> arrayList, String filePath) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
		out.writeObject(new Dimension(getWidth(), getHeight()));
		out.reset();
		out.writeObject(getBackgroundColor());
		out.reset();
		for(be.christopher.main.Point point : arrayList) {
			out.writeObject(point);
			out.reset();
		}
		out.close();
	}
	
	public void saveAsPng(String filePath) throws IOException {
		/* Image */
		int w = drawing.getWidth();
	    int h = drawing.getHeight();
	    BufferedImage draw = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = draw.createGraphics();
	    drawing.paint(g);
	    g.dispose();

	    File outputfile = new File(filePath);
		ImageIO.write(draw, "jpg", outputfile);
	}
	
	public void getFromFile(ArrayList<be.christopher.main.Point> arrayList, String pathname, String fileName) throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(pathname)));
		Object obj = null;
		int i = 0;
		name = fileName;
		while((obj = in.readObject()) != EOFException.class) {
			if(i > 1 && obj instanceof be.christopher.main.Point) {
				pointsList.add((be.christopher.main.Point) obj);
			}
			else if(i == 0 && obj instanceof Dimension) {
				Dimension dim = (Dimension) obj;
				width = (int) dim.getWidth();
				height = (int) dim.getHeight();
				i++;
			}
			else if(i == 1 && obj instanceof Color) {
				Color color = (Color) obj;
				backgroundColor = color;
				i++;
			}
		}
		in.close();
	}

	public JPanel getDrawing() {
		return drawing;
	}

	public void setDrawing(JPanel drawing) {
		this.drawing = drawing;
	}
	
}
