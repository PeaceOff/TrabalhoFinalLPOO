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
	
	/**
	 * 
	 * @param ip
	 * @param port
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public TCPClient(String ip, int port) throws UnknownHostException, IOException{
		
		socket = new Socket(ip, port); 
		rebindConnection(ip);   
		
	}

	/**
	 * 
	 * @param listener
	 */
	public void addConnectionListener(IClientConnection listener){
		m_IClientConnection.add(listener); 
	}

	/**
	 * 
	 * @param ip
	 * @throws IOException 
	 */
	private void rebindConnection(String ip) throws IOException{
		DataInputStream in = new DataInputStream(socket.getInputStream());
		int newPort = in.readInt(); 
		socket.close();
		
		socket = new Socket(ip,newPort);
		
		for(IClientConnection c : m_IClientConnection){
			c.ConnectedToServer(socket); 
		} 
		
	}

	/**
	 * 
	 * @param msg
	 * @throws IOException 
	 */
	public void sendInfo(byte[] msg) throws IOException{
		
		socket.getOutputStream().write(msg);
		
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