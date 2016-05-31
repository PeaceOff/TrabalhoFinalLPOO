package projeto.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
	private ServerInformationParser parser = new ServerInformationParser(8, true, this );
	private Vector2 dim;
	
	public GraphicLoop(){
		
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
	
	public Vector2 assertRatio(){
		Vector2 res = new Vector2();
		Vector2 windowDim = new Vector2();
		windowDim.x = this.getWidth();
		windowDim.y = this.getHeight();
		
		/*public final Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            Dimension prefSize = null;
            Component c = getParent();
            if (c == null) {
                prefSize = new Dimension(
                        (int)d.getWidth(),(int)d.getHeight());
            } else if (c!=null &&
                    c.getWidth()>d.getWidth() &&
                    c.getHeight()>d.getHeight()) {
                prefSize = c.getSize();
            } else {
                prefSize = d;
            }
            int w = (int) prefSize.getWidth();
            int h = (int) prefSize.getHeight();
            // the smaller of the two sizes
            int s = (w>h ? h : w);
            return new Dimension(s,s);
        }
        
        private void adjustSize() {
		    double width = getSize().width;
		    double height = getSize().height;
		    double ratio = 1.33;
		    double r = width / height;
		    if (r < ratio) {
		        width = height * ratio;
		        setSize((int) width, (int) height);
		    } else if (r > ratio) {
		        height = width / ratio;
		        setSize((int) width, (int) height);
		    }
		}
		
		ou tornar a janela unresizable frame.setResizable(false);
		
		JPanel p = new JPanel(new GridbagLayout());
		p.add(PCanvas,new GridbagConstraints());
		frame.add(p,Borderlayout.CENTER);
		
		Pesquisar gridbadlayout!
        */
		return res;
	}
	 
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 
		Graphics2D g2 = (Graphics2D)g;
		ArrayList<GameObject> go = mg.getGame_objects();

		
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
			
				g2.drawImage(temp
						, (int)dims.getxI(), (int)dims.getyI(), (int)dims.getxF(), (int)dims.getyF()
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
		g2.setFont(new Font("TimesNewRoman", Font.BOLD, 40));
		FontMetrics fm = g2.getFontMetrics();
		
		int width = fm.stringWidth(sb.toString());
		g2.drawString(sb.toString(), getWidth()/2 - width/2 , 40);
		
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
		
		if(info[0] == 'D')   
			mg.getInput().getPlayerInput(index).setDirection(new Vector2(info[1],info[2]));
	}

	@Override
	public void OnClientAttemptConnecting(Socket client) {
		System.out.println("Client Attempting Connection" 
							+ client.getInetAddress().getHostAddress()
							+ ":" + client.getPort() 
							+ " - " + client.getLocalPort());
	}

	@Override
	public void OnClientConnected(Socket client, int id) {
		mg.addPlayer(id);
		System.out.println("Client Connected" 
							+ client.getInetAddress().getHostAddress()
							+ ":" + client.getPort() 
							+ " - " + client.getLocalPort());
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write((byte)'D');
			out.write(mg.getDica().getBytes("UTF-8")); 
			server.sendInfo(InformationParser.transformInformation(out.toByteArray()), id); 
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		server.sendInfo(InformationParser.transformInformation("MBem-Vindo!".getBytes()), id); 
		server.sendInfo(InformationParser.transformInformation((byte)'A',(byte)id), id);
		
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
