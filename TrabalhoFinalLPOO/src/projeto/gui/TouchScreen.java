package projeto.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import java.awt.Point;

import projeto.network.InformationParser;
import projeto.network.UDPConnection;

public class TouchScreen extends JPanel implements MouseListener{

	
	public UDPConnection udp = null;
	Point p  = new Point();
	public TouchScreen() { 
		addMouseListener(this);
	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.blue); 
		g.fillOval(getWidth()/2 - 5, getHeight()/2 -5, 10, 10);
		g.fillOval(p.x-1, p.y-1, 2, 2);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	
	
	
}
