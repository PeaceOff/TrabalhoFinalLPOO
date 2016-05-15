package projeto.gui;

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
			temp.put(s, bi);
			return bi;
			
		} catch (IOException e) {
			return null;
		}
	}
	
	
}
