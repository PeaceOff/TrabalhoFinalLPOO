package projeto.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JPanel;

import projeto.logic.ControllerInformationPacket;
import projeto.logic.Estatistica;
import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Minigame;
import projeto.logic.Obj;
import projeto.logic.Rectangulo;
import projeto.logic.Vector2;
import projeto.logic.iMinigameAlert;
import projeto.minigames.shooter.ShooterGame;
import projeto.minigames.soccer.SoccerGame;
import projeto.minigames.survival.SurvivalMinigame;
import projeto.network.CommandParser;
import projeto.network.Host;
import projeto.network.IServerConnection;
import projeto.network.InformationParser;
import projeto.network.ServerInformationParser;
 
public class GraphicLoop extends JPanel implements Runnable , CommandParser, IServerConnection, iMinigameAlert {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Minigame mg = null;
	private Input in = new Input(8);
	private AtomicBoolean running = new AtomicBoolean(true);
	private TextureManager txtMng = new TextureManager();
	private Host server;
	private ServerInformationParser parser = new ServerInformationParser(8, true, this);
	private Vector2 dim;
	private int offset_x = 0;
	private int offset_y = 0;
	private double x_scale = 1;
	private double y_scale = 1;
	private MinigameSelector selector = new MinigameSelector(in,this);
	public GraphicLoop(){
		
		selector.SetWinnerString("Bem-Vindo");
		
		try {
			
			server = new Host(27015, 8); 
			server.setMessageParser(parser); 
			server.addListener(this);
			server.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void assertDim(double w, double h){
        
        int width = (int)w;
        int height = (int)h;
        width = (int) Math.min(width, height / (dim.y/dim.x));
        height = (int) Math.min(width * (dim.y/dim.x), height);
        x_scale = width/dim.x;
        y_scale = height/dim.y;
        offset_x = (int)((w - width)/ 2);
        offset_y = (int)((h - height) / 2);
	
	}
	 
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 
		
		Graphics2D g2 = (Graphics2D)g;
		
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);	
		
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);	
		hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);	
		
		
		g2.setRenderingHints(hints);
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		if(mg == null){ 
			selector.drawScene(g2);
			return; 
		}
		
		StringBuilder sb = new StringBuilder();
		
		synchronized(Minigame.class){
			ArrayList<GameObject> go = mg.getGame_objects();
			assertDim(g2.getDeviceConfiguration().getBounds().getWidth(),g2.getDeviceConfiguration().getBounds().getHeight());
			
	
			g2.setColor(Color.gray);
			g2.fillRect(offset_x, offset_y, (int)(dim.x*x_scale), (int)(dim.y*y_scale));
			
			
			synchronized(go){ 
				for(GameObject gO: go){ 
					
					Obj obj = gO.getObj();
					
					Rectangulo dims = obj.getDimensions();
					Rectangulo subI = obj.getSubImage();
					
					BufferedImage temp = txtMng.getTexture(obj.getPath());
					
				
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
			
			g2.setColor(Color.black);
			for(int i = 0; i < mg.getScores().length; i ++){
				if(i != mg.getScores().length-1)
					sb.append(mg.getScores()[i]).append(':');
				else
					sb.append(mg.getScores()[i]);
			}
		}
		g2.setFont(new Font("TimesNewRoman", Font.BOLD, (int)(40 * y_scale)));
		FontMetrics fm = g2.getFontMetrics();
		
		int width = fm.stringWidth(sb.toString());
		g2.drawString(sb.toString(),getWidth()/2 - width/2, (int)((35 * y_scale) + offset_y));
		
	}
	
	@Override
	public void run() {
		
		double time = 0;
		
		long lastTime = System.nanoTime();
		
		final int FPS = 144;
		final long TEMPO = 1000000000/FPS;
		
		
		while(running.get()){
			
			long agora = System.nanoTime();
			long updateLength = agora - lastTime;
			lastTime = agora; 
			double deltaTime = updateLength/1000000000.0;
			
			System.out.println(deltaTime);
			 
			if(mg!=null){
			synchronized(Minigame.class){
				if(mg!=null)
					mg.update((float)deltaTime);
			}
			}else{
				selector.update((float)deltaTime);
				
				if(selector.escolhidoMG()){
					mg = selector.escolherMinijogo();
					dim = mg.getDim();					
					mg.initGame();	
					sendControlLayoutAll();
					AddPlayers();
					
					
				}
				
			}
			
			repaint();
			
			try {
				
				long tempoEspera = (TEMPO - (System.nanoTime() - lastTime))/1000000;

				if(tempoEspera > 0)
						Thread.sleep( tempoEspera );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	
	public void AddPlayers(){
		synchronized(Minigame.class){
			boolean[] con = server.getConnected();
			for(int i = 0; i < con.length; i++)
				if(con[i])
					mg.addPlayer(i);
		}
	}
	
	@Override
	public void parseCMD(byte[] info, int index) {
		if(mg == null) return; 
		
		ByteArrayInputStream inputStream = null;
		ObjectInputStream objIn;
		switch(info[0]){
		case 'C':
			switch(info[1]){
			case 'J':
				inputStream = new ByteArrayInputStream(info,2,info.length); 
				try {
					objIn = new ObjectInputStream(inputStream);
					Vector2 res = (Vector2)objIn.readObject();
					byte pos = (byte)objIn.readObject();
					
					if(res != null)
						in.getPlayerInput(index).setDirection(pos,res);
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 'B':
				inputStream = new ByteArrayInputStream(info,2,info.length); 
				try {
					objIn = new ObjectInputStream(inputStream);
					byte value = (byte)objIn.readObject();
					byte pos = (byte)objIn.readObject();

					in.getPlayerInput(index).setKey(pos, value); 
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
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
				server.sendInfo(InformationParser.transformInformation(out.toByteArray()), id);
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void sendControlLayoutAll(){
		if(mg != null){
			
			for(int i =0 ; i < server.getConnected().length; i++)
				if(server.getConnected()[i])
					try {
						server.sendInfo(InformationParser.transformInformation(('D' + mg.getDica()).getBytes("UTF-8")), i);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			ArrayList<ControllerInformationPacket> packets = mg.getControllPacket();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write('C');
			
			try {
				
				ObjectOutputStream objOut = new ObjectOutputStream(out);
				
				objOut.writeObject(packets);
				server.sendInfoAll(InformationParser.transformInformation(out.toByteArray()));
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void OnClientConnected(Socket client, int id) {
		server.sendInfo(InformationParser.transformInformation((byte)'A',(byte)id), id);
		if(mg == null) return; 
		synchronized(Minigame.class){
			mg.addPlayer(id);
		}
		System.out.println("Client Connected" 
							+ client.getInetAddress().getHostAddress()
							+ ":" + client.getPort() 
							+ " - " + client.getLocalPort());
		
		try {
			server.sendInfo(InformationParser.transformInformation(('D' + mg.getDica()).getBytes("UTF-8")), id);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		sendControlLayout(id);
		
	}

	@Override
	public void OnClientDisconnected(Socket client, int id) {
		if(mg != null)
			synchronized(Minigame.class){
				mg.removePlayer(id);
			}
		
		System.out.println("Client Disconnected"  
							+ client.getInetAddress().getHostAddress()
							+ ":" + client.getPort() 
							+ " - " + client.getLocalPort());
	}

	@Override
	public void receiveEstatistica(int player_id, Estatistica e) {
		
		server.sendInfo(InformationParser.transformInformation(("M" + e.getNome()).getBytes()), player_id); 
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			stream.write(new byte[]{(byte)'S'}); 
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			ObjectOutputStream ss = new ObjectOutputStream(stream);
			
			ss.writeObject(e);
			
			server.sendInfo(InformationParser.transformInformation(stream.toByteArray() ), player_id);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
	}

	@Override
	public void gameEnded(String vencedor) {
		selector.SetWinnerString(vencedor);
		
		(new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized(Minigame.class){
					
					mg.acabar();
					mg = null;
				}
				
			}
		})).start();
		
	}
	
	
	
}
