package projeto.network;

import java.io.IOException;
import java.net.*;

/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:29
 */
public class UDPConnection extends Thread{

	private final int id;
	private String ipConfirmation;
	private DatagramSocket socket;
	private IMessage m_IMessage;
	String ip = null; 
	int port = 3333;
	
	/**
	 * 
	 * @param host
	 * @param port
	 * @throws SocketException 
	 */
	public UDPConnection(String host, int port, int id) throws SocketException{
		this.id  = id;
		ip = host;
		this.port=port;
		socket = new DatagramSocket(port); 
		
	}

	/**
	 * 
	 * @param info
	 * @throws  
	 */
	public void sendInfo(byte[] info) {
		
		try {
			InetAddress addr = InetAddress.getByName(ip);
			
			if(addr == null)return;
			
			DatagramPacket packet =new DatagramPacket(info, info.length, addr, port);
			
			
			socket.send(packet);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setMessageParser(IMessage m){
		m_IMessage = m;
	}
	
	@Override
	public void run() {
		
		while(socket!=null){

			byte[] info = new byte[255];

			DatagramPacket packet = new DatagramPacket(info, info.length);

			try {
				socket.receive(packet);
				
				if(packet.getAddress().getHostAddress() != ipConfirmation) continue;  
				
				m_IMessage.OnMessageReceived(packet.getData(), id);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		}
		
	}
	
	
	public void close(){
		if(socket != null)
			socket.close();
		socket = null;
	}

}