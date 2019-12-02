package com.roshan.main;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class PenSettings extends JFrame implements ChangeListener {

	/* ATTRIBUTS */
	private Model model;
	private JPanel contentPane;
	
	/* CONSTRUCTOR */
	public PenSettings(Model model) {
		setTitle("Drawing - Pen's settings");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jlTitle = new JLabel("PEN'S SETTINGS");
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jlTitle.setBounds(10, 11, 414, 31);
		contentPane.add(jlTitle);
		
		JLabel jlSelectShape = new JLabel("Select shape");
		jlSelectShape.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlSelectShape.setBounds(26, 73, 93, 31);
		contentPane.add(jlSelectShape);
		
		JComboBox jcbSelectShape = new JComboBox();
		jcbSelectShape.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jcbSelectShape.setBounds(26, 104, 150, 31);
		contentPane.add(jcbSelectShape);
		for(String shape : model.getShapesList())
			jcbSelectShape.addItem(shape);
		jcbSelectShape.setSelectedItem(model.getShape());
		
		JLabel jlSize = new JLabel("Size : ");
		jlSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlSize.setBounds(252, 73, 93, 31);
		contentPane.add(jlSize);
		
		JSlider jsSize = new JSlider();
		jsSize.setValue(model.getPenSize());
		jsSize.setMinimum(2);
		jsSize.setBounds(252, 104, 150, 31);
		contentPane.add(jsSize);
		Timer timer = new Timer(1, e -> {
			jlSize.setText("Size : " + model.getPenSize());
		});
		timer.start();
		
		JLabel jlSelectColor = new JLabel("Select color");
		jlSelectColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlSelectColor.setBounds(26, 159, 93, 31);
		contentPane.add(jlSelectColor);
		
		Button jbClose = new Button("Close");
		jbClose.setFont(new Font("Dialog", Font.PLAIN, 14));
		jbClose.setBackground(UIManager.getColor("Button.light"));
		jbClose.setBounds(252, 190, 150, 31);
		contentPane.add(jbClose);
		
		Button jbSelectColor = new Button("Color");
		jbSelectColor.setFont(new Font("Dialog", Font.PLAIN, 14));
		jbSelectColor.setBackground(SystemColor.controlHighlight);
		jbSelectColor.setBounds(26, 190, 150, 31);
		contentPane.add(jbSelectColor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 53, 376, 9);
		contentPane.add(separator);
		setVisible(true);
		
		model.setXY(-1, -1);
		jcbSelectShape.addActionListener(e -> {
			model.setShape(jcbSelectShape.getSelectedItem().toString());
		});
		
		jsSize.addChangeListener(e -> {
			model.setPenSize(jsSize.getValue());
		});
		
		jbSelectColor.addActionListener(e -> {
			JColorChooser jccColor = new JColorChooser();
			Color color = jccColor.showDialog(this, "Colors", model.getColor());
			model.setColor(color);
		});
		
		jbClose.addActionListener(e -> {
			dispose();
		});
		
		setLocationRelativeTo(null);
	}
	
	/* METHODS */
	@Override
	public void stateChanged(ChangeEvent e) {
		
	}
	
	public void setModel(Model model) {
		if(model == null)
			throw new NullPointerException("The specified template does not exist !");
		this.model = model;
		this.repaint();
	}
	
}
