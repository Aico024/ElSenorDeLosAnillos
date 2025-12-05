package interfacesGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Cursor;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setPreferredSize(new Dimension(700, 400));
		setTitle("EL SEÑOR DE LOS ANILLOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 80));
		panel.setBackground(new Color(255, 128, 128));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 192));
		panel_1.setPreferredSize(new Dimension(200, 10));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		panel_1.setBorder(new EmptyBorder(10, 15, 10, 15));
		
		JPanel panel_6 = new JPanel();
		panel_6.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_6.setBackground(new Color(0, 128, 64));
		panel_1.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(64, 128, 128));
		panel_6.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));
		panel_7.setBorder(new EmptyBorder(20, 10, 20, 10));

		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_7.add(panel_10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(64, 128, 128));
		panel_6.add(panel_8);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 128));
		panel_3.setPreferredSize(new Dimension(10, 50));
		panel_2.add(panel_3, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(128, 255, 128));
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		panel_4.setBorder(new EmptyBorder(10, 75, 10, 75));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);

	}
}
