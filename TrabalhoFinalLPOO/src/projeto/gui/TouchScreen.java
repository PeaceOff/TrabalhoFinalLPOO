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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(udp == null) return;
		int x = getWidth()/2;
		int y = getHeight()/2;
		p = e.getPoint();
		x = e.getX() - x;
		y = e.getY() - y;
		
		double module = Math.sqrt(x*x + y*y);
		byte xf = (byte)((x/module)*100); 
		byte yf = (byte)((y/module)*100);
		udp.sendInfo( InformationParser.transformInformation((byte)'D', xf, yf) );
		repaint();
	}
	
	
	
	
}
