package projeto.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.layout.Border;

public class MainWindow extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {  
				try {
					MainWindow frame = new MainWindow(); 
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
	public MainWindow() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		
		GraphicLoop gameLoop = new GraphicLoop();
		gameLoop.setVisible(true);
		setContentPane(gameLoop);
		(new Thread(gameLoop)).start();
		
	}

}
