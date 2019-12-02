package com.roshan.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Controller extends JMenuBar {

	/* ATTRIBUTES */
	private Model model;
	
	/* CONSTRUCTOR */
	public Controller(Model model) {
		setModel(model);
		
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiSaveProject = new JMenuItem("Save project");
		JMenuItem jmiSaveAsPng = new JMenuItem("Save as png");
		JMenuItem jmiExit = new JMenuItem("Exit");
		jmFile.add(jmiSaveProject);
		jmFile.add(jmiSaveAsPng);
		jmFile.add(jmiExit);
		add(jmFile);
		
		JMenu jmOptions = new JMenu("Options");
		JMenuItem jmiBackgroundColor = new JMenuItem("Background settings");
		JMenuItem jmiPen = new JMenuItem("Pen settings");
		JMenuItem jmiResetDraw = new JMenuItem("Reset drawing");
		JMenuItem jmiResetOptions = new JMenuItem("Reset options");
		jmOptions.add(jmiBackgroundColor);
		jmOptions.add(jmiPen);
		jmOptions.add(jmiResetDraw);
		jmOptions.add(jmiResetOptions);
		add(jmOptions);
		
		
		jmiSaveProject.addActionListener(e -> {
			JFileChooser jfcChooser = new JFileChooser("C:");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
			jfcChooser.setFileFilter(filter);
			int value = jfcChooser.showSaveDialog(this);
			
			if(value == JFileChooser.APPROVE_OPTION)  {
				String fileName = Utils.getNameWithoutExtension(jfcChooser.getSelectedFile().getPath());
				try {
					model.saveProject(model.getPointsList(), fileName + ".txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		jmiSaveAsPng.addActionListener(e -> {
			JFileChooser jfcChooser = new JFileChooser("C:");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "png");
			jfcChooser.setFileFilter(filter);
			int value = jfcChooser.showSaveDialog(this);
			
			if(value == JFileChooser.APPROVE_OPTION)  {
				String fileName = Utils.getNameWithoutExtension(jfcChooser.getSelectedFile().getPath());
				try {
					model.saveAsPng(fileName + ".png");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		jmiPen.addActionListener(e -> {
			PenSettings pvPen = new PenSettings(model);
		});
		
		jmiBackgroundColor.addActionListener(e -> {
			JColorChooser jccColor = new JColorChooser();
			@SuppressWarnings("static-access")
			Color color = jccColor.showDialog(this, "Colors", model.getBackgroundColor());
			model.setBackgroundColor(color);
		});
		
		jmiResetDraw.addActionListener(e -> {
			model.setXY(-1, -1);
			model.getPointsList().clear();
		});
		
		jmiResetOptions.addActionListener(e -> {
			model.setBackgroundColor(Color.WHITE);
			model.setColor(Color.BLACK);
			model.setShape("Circle");
			model.setPenSize(5);
			model.setXY(-1, -1);
		});
		
		jmiExit.addActionListener(e -> {
			System.exit(0);
		});
	}
	
	/* METHODS */
	public void setModel(Model model) {
		if(model == null)
			throw new NullPointerException("The specified template does not exist !");
		this.model = model;
	}
	
}
