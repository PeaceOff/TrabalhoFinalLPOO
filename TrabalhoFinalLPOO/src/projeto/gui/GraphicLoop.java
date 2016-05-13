package projeto.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Minigame;
import projeto.logic.Obj;
import projeto.logic.Rectangulo;
import projeto.logic.SoccerGame;
 
public class GraphicLoop extends JPanel implements Runnable{
	
	private Minigame mg;
	private Input in;
	AtomicBoolean running = new AtomicBoolean(true);
	double lastTime = 0;
	BufferedImage img;
	BufferedImage img2;
	
	public GraphicLoop(){
		in = new Input(); 
		mg = new SoccerGame(in);
		mg.initGame();
		try {
			img =  ImageIO.read(new File("colors-originals.png"));
			img2 =  ImageIO.read(new File("circulo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		for(GameObject gO: mg.getGame_objects()){
			Obj obj = gO.getObj();
			Rectangulo dims = obj.getDimensions();
			Rectangulo subI = obj.getSubImage();
			BufferedImage temp = img;
			if(obj.getPath() == "circulo.png"){
				temp=img2;
			}
			
			/*System.out.println("Coords:" 
							+dims.getxI() +" , " 
							+dims.getyI() +" , " 
							+dims.getxF() +" , " 
							+dims.getyF()); 
				/**/ 									
			g.drawImage(temp
					, (int)dims.getxI(), (int)dims.getyI(), (int)dims.getxF(), (int)dims.getyF()
					,(int) (subI.getxI() * temp.getWidth()) 
					,(int) (subI.getyI() * temp.getHeight())
					,(int) (subI.getxF() * temp.getWidth())
					,(int) (subI.getyF() * temp.getHeight()) 
					, null); 
			
		}
		
	}
	
	@Override
	public void run() {
		
		while(running.get()){
		
			if(lastTime == 0){
				lastTime = System.currentTimeMillis();
				continue;
			}
			double deltaTime = (System.currentTimeMillis()-lastTime)/1000;
			
			synchronized(mg){
				mg.update((float)deltaTime);
			} 
			revalidate();
			repaint();
			
			lastTime = System.currentTimeMillis();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	
	
}
