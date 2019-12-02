package com.roshan.main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class View extends JPanel implements ChangeListener {

	/* ATTRIBUTES */
	private Model model;
	
	/* CONSTRUCTOR */
	public View(Model model) {
		setModel(model);
		model.addView(this);
		model.setDrawing(this);
		setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				model.setXY(e.getX(), e.getY());
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				model.setXY(e.getX(), e.getY());
			}
		});
		
		addMouseWheelListener(e -> {
			model.setXY(-1, -1);
			if(e.getWheelRotation() < 0) {
				model.setPenSize(model.getPenSize() + 1);
			}
			else {
				model.setPenSize(model.getPenSize() - 1);
			}
		});
		
		repaint();
	}
	
	/* METHODS */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(model.getBackgroundColor());
		
		if(model.getX() != -1 && model.getY() != -1) {
			model.getPointsList().add(new be.christopher.main.Point(model.getX(), model.getY(), model.getColor(), model.getPenSize(), model.getShape()));
		}
		
		if(model.getPointsList().size() > 0) {
			for(be.christopher.main.Point point : model.getPointsList()) {
				g.setColor(point.getColor());
				
				switch (point.getShape()) {
				case "Circle":
					g.fillOval((int) point.getX() - point.getSize() / 2, (int) point.getY() - point.getSize() / 2, point.getSize(), point.getSize());
					break;
				case "Rectangle":
					g.fillRect((int) point.getX() - point.getSize() / 2, (int) point.getY() - point.getSize() / 2, point.getSize(), point.getSize());
					break;
				default:
					g.fillOval((int) point.getX() - point.getSize() / 2, (int) point.getY() - point.getSize() / 2, point.getSize(), point.getSize());
					break;
				}
			}
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		this.repaint();
	}
	
	public void setModel(Model model) {
		if(model == null)
			throw new NullPointerException("The specified template does not exist !");
		this.model = model;
		this.repaint();
	}
	
}
