package projeto.gui;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;


public class TextureManager {
	
	private HashMap<String,BufferedImage> temp = new HashMap<String, BufferedImage>();
	
	
	public BufferedImage getTexture(String s){
		
		BufferedImage bi = temp.get(s);
		
		if(bi != null)
			return bi;
		
		try {
			bi = ImageIO.read(new File(s));
			
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice device = env.getDefaultScreenDevice();
			GraphicsConfiguration config = device.getDefaultConfiguration();
			BufferedImage buffy = config.createCompatibleImage(bi.getWidth(), bi.getHeight(), Transparency.BITMASK);
			Graphics2D g2 = buffy.createGraphics();
			g2.drawImage(bi, 0, 0, buffy.getWidth(), buffy.getHeight(), 0, 0, bi.getWidth(), buffy.getHeight(), null);
			g2.dispose();
			
			temp.put(s, buffy);
			return bi;
			
		} catch (IOException e) {
			return null;
		}
	}
	
	
}
