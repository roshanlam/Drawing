import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	private JTextField jtfName;
	
	/* CONSTRUCTOR */
	public Main(String title) {
		super(title);
		setResizable(false);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		
		/* ========== JPANEL LEFT ========== */
		JPanel jbPanelLeft = new JPanel();
		jbPanelLeft.setBackground(Color.DARK_GRAY);
		jbPanelLeft.setBounds(0, 0, 275, 441);
		getContentPane().add(jbPanelLeft);
		jbPanelLeft.setLayout(null);
		
		JLabel jlTitle = new JLabel("Drawing");
		jlTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		jlTitle.setForeground(Color.LIGHT_GRAY);
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlTitle.setBounds(10, 11, 255, 46);
		jbPanelLeft.add(jlTitle);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 68, 195, 2);
		jbPanelLeft.add(separator);
		
		Button jbCreate = new Button("CREATE");
		jbCreate.setForeground(Color.WHITE);
		jbCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbCreate.setBackground(SystemColor.activeCaption);
		jbCreate.setBounds(39, 108, 195, 39);
		jbPanelLeft.add(jbCreate);
		
		Button jbExit = new Button("EXIT");
		jbExit.setForeground(Color.WHITE);
		jbExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbExit.setBackground(SystemColor.activeCaption);
		jbExit.setBounds(39, 228, 195, 39);
		jbPanelLeft.add(jbExit);
		
		Button jbLoad = new Button("LOAD");
		jbLoad.setForeground(Color.WHITE);
		jbLoad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jbLoad.setBackground(SystemColor.activeCaption);
		jbLoad.setBounds(39, 168, 195, 39);
		jbPanelLeft.add(jbLoad);
		
		
		/* ========== JPANEL RIGHT ========== */
		JPanel jbPanelRight = new JPanel();
		jbPanelRight.setBackground(Color.WHITE);
		jbPanelRight.setBounds(274, 0, 430, 441);
		getContentPane().add(jbPanelRight);
		jbPanelRight.setLayout(null);
		
		jtfName = new JTextField();
		jtfName.setText("My Project");
		jtfName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jtfName.setBounds(40, 65, 355, 39);
		jbPanelRight.add(jtfName);
		jtfName.setColumns(10);
		
		JLabel jlName = new JLabel("PROJECT NAME");
		jlName.setBackground(Color.BLACK);
		jlName.setForeground(Color.BLACK);
		jlName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlName.setBounds(41, 37, 105, 25);
		jbPanelRight.add(jlName);
		
		JSpinner jsWidth = new JSpinner();
		jsWidth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jsWidth.setModel(new SpinnerNumberModel(new Integer(720), null, null, new Integer(1)));
		jsWidth.setBounds(40, 172, 355, 39);
		jbPanelRight.add(jsWidth);
		
		JLabel jlWidth = new JLabel("WIDTH");
		jlWidth.setForeground(Color.BLACK);
		jlWidth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlWidth.setBackground(Color.BLACK);
		jlWidth.setBounds(41, 141, 105, 25);
		jbPanelRight.add(jlWidth);
		
		JSpinner jsHeight = new JSpinner();
		jsHeight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jsHeight.setModel(new SpinnerNumberModel(new Integer(480), null, null, new Integer(1)));
		jsHeight.setBounds(40, 282, 355, 39);
		jbPanelRight.add(jsHeight);
		
		JLabel jlHeight = new JLabel("HEIGHT");
		jlHeight.setForeground(Color.BLACK);
		jlHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlHeight.setBackground(Color.BLACK);
		jlHeight.setBounds(41, 251, 105, 25);
		jbPanelRight.add(jlHeight);
		setBounds(100, 100, 720, 395);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		/* ========== LISTENERS ========== */
		jbCreate.addActionListener(e -> {
			if(jtfName.getText().length() > 0) {
				Model model = new Model(jtfName.getText(), (int) jsWidth.getValue(), (int) jsHeight.getValue());
				MainFrame window = new MainFrame(model);
				Controller controller = new Controller(model);
				View view = new View(model);
				window.add(controller, BorderLayout.NORTH);
				window.add(view, BorderLayout.CENTER);
				window.pack();
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(jbCreate, "You must defined a name to your project.", "Drawing - Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		jbLoad.addActionListener(e -> {
			Model model = new Model(jtfName.getText(), (int) jsWidth.getValue(), (int) jsHeight.getValue());
			JFileChooser jfcChooser = new JFileChooser("C:");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
			jfcChooser.setFileFilter(filter);
			jfcChooser.showOpenDialog(this);
			
			if(jfcChooser.getSelectedFile() != null)  {
				try {
					model.getFromFile(model.getPointsList(), jfcChooser.getSelectedFile().getPath(), Utils.getNameWithoutExtension(jfcChooser.getSelectedFile().getName()));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainFrame window = new MainFrame(model);
				Controller controller = new Controller(model);
				View view = new View(model);
				window.add(controller, BorderLayout.NORTH);
				window.add(view, BorderLayout.CENTER);
				window.pack();
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				dispose();
			}
		});
		
		jbExit.addActionListener(e -> {
			System.exit(0);
		});
		
		setLocationRelativeTo(null);
	}

	/* MAIN */
	public static void main(String[] args) {
		try {
            // Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		
		Main window = new Main("Drawing - Project creation");
		window.setVisible(true);
		
	}

}
