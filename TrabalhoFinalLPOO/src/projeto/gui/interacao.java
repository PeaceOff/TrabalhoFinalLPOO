package projeto.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import projeto.logic.SoccerGame;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class interacao {

	private JFrame frame;
	private SoccerGame sG;
	private double curTime;
	private double lastTime;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interacao window = new interacao();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public interacao() {
		initialize();
		sG.initGame();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Bem-Vindo!");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		

		
	}
}
