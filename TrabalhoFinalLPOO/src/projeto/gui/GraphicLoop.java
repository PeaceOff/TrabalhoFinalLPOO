package projeto.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import projeto.logic.ControllerInformationPacket;
import projeto.logic.Estatistica;
import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Minigame;
import projeto.logic.Obj;
import projeto.logic.Rectangulo;
import projeto.logic.Vector2;
import projeto.logic.iEstatisticaAlert;
import projeto.minigames.soccer.SoccerGame;
import projeto.network.CommandParser;
import projeto.network.Host;
import projeto.network.ICommandReceived;
import projeto.network.IServerConnection;
import projeto.network.InformationParser;
import projeto.network.ServerInformationParser;
 
public class GraphicLoop extends JPanel implements Runnable , CommandParser, IServerConnection, iEstatisticaAlert {
	
	private Minigame mg;
	private Input in;
	private AtomicBoolean running = new AtomicBoolean(true);
	private double lastTime = 0;
	private TextureManager txtMng = new TextureManager();
	private Host server;
	private ServerInformationParser parser = new ServerInformationParser(8, true, this);
	private Vector2 dim;
	private int offset_x;
	private int offset_y;
	private double x_scale;
	private double y_scale;
	private JFrame parent;
	
	public GraphicLoop(JFrame p){
		
		
		parent = p;
		in = new Input(8);  
		mg = new SoccerGame(in,this);
		dim = mg.getDim();
		mg.initGame();
		
		try {
			
			server = new Host(27015, 8); 
			server.setMessageParser(parser); 
			server.addListener(this);
			server.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void assertDim(){
        
        int width = this.getWidth();
        int height = this.getHeight();
        width = (int) Math.min(width, height / (dim.y/dim.x));
        height = (int) Math.min(width * (dim.y/dim.x), height);
        offset_x = ((getWidth() - width)/ 2);
        offset_y = ((getHeight() - height) / 2);
        x_scale = width/dim.x;
        y_scale = height/dim.y;
	
	}
	 
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 
		Graphics2D g2 = (Graphics2D)g;
		ArrayList<GameObject> go = mg.getGame_objects();
		assertDim();
		
		synchronized(go){ 
			for(GameObject gO: go){ 
				
				Obj obj = gO.getObj();
				
				Rectangulo dims = obj.getDimensions();
				Rectangulo subI = obj.getSubImage();
				 
				BufferedImage temp = txtMng.getTexture(obj.getPath());
				
				
				RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);	
				
				hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);	
				hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);	
				
				
				g2.setRenderingHints(hints);
			
				g2.drawImage(temp,
							(int)((dims.getxI()* x_scale) + offset_x), (int)((dims.getyI() * y_scale) + offset_y),
							(int)((dims.getxF() * x_scale)  + offset_x), (int)((dims.getyF() * y_scale) + offset_y)
						,(int) (subI.getxI() * temp.getWidth()) 
						,(int) (subI.getyI() * temp.getHeight())
						,(int) (subI.getxF() * temp.getWidth())
						,(int) (subI.getyF() * temp.getHeight()) 
						, null); 
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < mg.getScores().length; i ++){
			if(i != mg.getScores().length-1)
				sb.append(mg.getScores()[i]).append(':');
			else
				sb.append(mg.getScores()[i]);
		}
		g2.setFont(new Font("TimesNewRoman", Font.BOLD, (int)(40 * y_scale)));
		FontMetrics fm = g2.getFontMetrics();
		
		int width = fm.stringWidth(sb.toString());
		g2.drawString(sb.toString(),getWidth()/2 - width/2, (int)((39 * y_scale) + offset_y));
		
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

	@Override
	public void parseCMD(byte[] info, int index) {
		
		switch(info[0]){
		case 'C':
			switch(info[1]){
			case 'J':
				ByteArrayInputStream inputStream = new ByteArrayInputStream(info,2,info.length); 
				ObjectInputStream objIn;
				try {
					objIn = new ObjectInputStream(inputStream);
					Vector2 res = (Vector2)objIn.readObject();
					if(res != null)
						mg.getInput().getPlayerInput(index).setDirection(res);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				break;
			}
			break;
		}
	}

	@Override
	public void OnClientAttemptConnecting(Socket client) {
		System.out.println("Client Attempting Connection" 
							+ client.getInetAddress().getHostAddress()
							+ ":" + client.getPort() 
							+ " - " + client.getLocalPort());
	}

	private void sendControlLayout(int id){
		if(mg != null){
			
			ArrayList<ControllerInformationPacket> packets = mg.getControllPacket();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write('C');
			
			try {
				
				ObjectOutputStream objOut = new ObjectOutputStream(out);
				
				objOut.writeObject(packets);
				System.out.println("Tamanho Controller:" + out.toByteArray().length);
				server.sendInfo(InformationParser.transformInformation(out.toByteArray()), id);
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
	}
	
	@Override
	public void OnClientConnected(Socket client, int id) {
		mg.addPlayer(id);
		System.out.println("Client Connected" 
							+ client.getInetAddress().getHostAddress()
							+ ":" + client.getPort() 
							+ " - " + client.getLocalPort());
		
		try {
			server.sendInfo(InformationParser.transformInformation(('D' + mg.getDica()).getBytes("UTF-8")), id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
		server.sendInfo(InformationParser.transformInformation("MBem-Vindo!".getBytes()), id); 
		server.sendInfo(InformationParser.transformInformation((byte)'A',(byte)id), id);
		sendControlLayout(id);
		
	}

	@Override
	public void OnClientDisconnected(Socket client, int id) {
		mg.removePlayer(id); 
		System.out.println("Client Disconnected"  
							+ client.getInetAddress().getHostAddress()
							+ ":" + client.getPort() 
							+ " - " + client.getLocalPort());
	}

	@Override
	public void receiveEstatistica(int player_id, Estatistica e) {
		
		server.sendInfo(InformationParser.transformInformation(("M" + e.getNome()).getBytes()), player_id); 
		
		System.out.println("-----Player:" + player_id + " " + e.getTipoJogo() + " "+ e.getNome() + ":" + e.getValor());
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			stream.write(new byte[]{(byte)'S'}); 
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			ObjectOutputStream ss = new ObjectOutputStream(stream);
			
			ss.writeObject(e);
			
			server.sendInfo(InformationParser.transformInformation(stream.toByteArray() ), player_id);
			System.out.println("DDDDDMSG Sent :" + player_id + ":" + stream.toByteArray().toString());
			System.out.println("Tamanho" + stream.toByteArray().length); 
			
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	
	
}
