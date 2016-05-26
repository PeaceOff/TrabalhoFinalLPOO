package projeto.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:28
 */
public class TCPClient extends TCPBasic implements Runnable {

	private Socket socket = null;
	private ArrayList<IClientConnection> m_IClientConnection = new ArrayList<IClientConnection>();
	private String ip;
	private int port;
	/**
	 * 
	 * @param ip
	 * @param port
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public TCPClient(String ip, int port) throws UnknownHostException, IOException{
		this.ip = ip;
		this.port = port; 
		connect(); 
		
	}
	
	public TCPClient(String ip, int port, IClientConnection listener) throws UnknownHostException, IOException{
		this.ip = ip;
		this.port = port;
		m_IClientConnection.add(listener); 
		//connect();
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		close(); 
		super.finalize();
	}


	/**
	 * 
	 * @param listener
	 */
	public void addConnectionListener(IClientConnection listener){
		m_IClientConnection.add(listener); 
	}
	
	public void connect() throws IOException{
		socket = new Socket(ip, port); 
		rebindConnection(ip);  
	}
	
	public void close(){
		if(socket == null) return;
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket = null;
	}
	
	/**
	 * 
	 * @param ip
	 * @throws IOException 
	 */
	public void rebindConnection(String ip) throws IOException{
		DataInputStream in = new DataInputStream(socket.getInputStream());
		int newPort = in.readInt(); 
		
		if(newPort == 0){
			
			for(IClientConnection c : m_IClientConnection)
				c.ServerFull(socket);
			 
			socket.close();
			return;
		}
		
		socket.close();
		 
		socket = new Socket(ip,newPort);
		
		for(IClientConnection c : m_IClientConnection)
			c.ConnectedToServer(socket); 
		
		
	}

	/**
	 * 
	 * @param msg
	 * @throws IOException 
	 */
	public void sendInfo(byte[] msg) throws IOException{
		
		socket.getOutputStream().write(msg);
		
	}
	
	public int getPort(){
		return socket.getPort();
	}

	@Override
	public void run() {
		while(socket!=null){
			
			byte[] info = new byte[255];
			
			try {
				int readed = socket.getInputStream().read(info);
				byte[] msg = new byte[readed];
				for(int i =0 ; i < readed; i++){
					msg[i] = info[i];
				}
				
				 
				m_IMessage.OnMessageReceived(msg, 0); //ID!!!! 
				
				
			} catch (IOException e) {
				
				for(IClientConnection c : m_IClientConnection){
					c.DisconnectedFromServer(socket);
				}
				 
				try {
					if(socket != null)
						socket.close();
					socket = null;
				} catch (IOException e1) {
					socket = null; 
					e1.printStackTrace();
				}
				
				e.printStackTrace();
			}
			
		}
	}

}