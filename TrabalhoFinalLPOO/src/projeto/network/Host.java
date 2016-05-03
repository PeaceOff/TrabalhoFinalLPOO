package projeto.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;


/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:23:55
 */
public class Host extends Thread implements IServerConnection{

	private int basePort = 3333;
	private ServerSocket svSocket = null;
	private TCPServerClient[] m_TCPConnection = null;
	private UDPConnection[] m_UDPConnection = null; 
	private ArrayList<IServerConnection> m_IServerConnection = new ArrayList<IServerConnection>();
	private int numClients = 10;
	private int numClientsConnected = 0;
	private IMessage messageParser = null;

	public Host(int port, int numClients) throws IOException{
		basePort = port;
		this.numClients = numClients;
		
		m_TCPConnection = new TCPServerClient[numClients];
		m_UDPConnection = new UDPConnection[numClients];
		
		svSocket = new ServerSocket(port);
	}

	/**
	 * 
	 * @param listener
	 */
	public void addListener(IServerConnection listener){
		m_IServerConnection.add(listener);
	}
	
	public void setMessageParser(IMessage m){
		messageParser = m;
	}

	public int getConnectionsNumber(){	
		return numClientsConnected; 
	}

	private int getOpenPort(){
		for(int i = 0; i < m_TCPConnection.length ; i++){
			if(m_TCPConnection[i] == null)
				return i;
		} 
		return -1; 
	}

	/**
	 * 
	 * @param info
	 * @param id
	 */
	public void sendInfo(byte[] info, int id){
		
		if(id >= m_TCPConnection.length) return;
		
		if(m_TCPConnection[id] == null) return;
		
		m_TCPConnection[id].sendInfo(info);
		
	}

	/**
	 * 
	 * @param info
	 */
	public void sendInfoAll(byte[] info){
		
		synchronized(m_TCPConnection){
			for(TCPServerClient c : m_TCPConnection){
				if(c == null) continue;
				
				c.sendInfo(info);
			}
		}
	}
	
	@Override
	public void run() {
		
		while(numClientsConnected < numClients){
			
			try {
				
				Socket tempClient = svSocket.accept();
				int pos = getOpenPort();
				int newPort = pos + basePort + 1;
				 
				TCPServerClient newConnection = new TCPServerClient(newPort, pos);
				newConnection.setMessageListener(messageParser); 
				m_TCPConnection[pos] = newConnection;
				
				
				(new Thread(newConnection)).start();
				newConnection.addConnectionListener(this);
				
				DataOutputStream out = new DataOutputStream(tempClient.getOutputStream());
				out.writeInt(newPort);
				
				tempClient.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void OnClientAttemptConnecting(Socket client) {
		for(IServerConnection c : m_IServerConnection)
			c.OnClientAttemptConnecting(client);
		
		
	}

	@Override
	public void OnClientConnected(Socket client, int id) {
		
		numClientsConnected++;
		
		for(IServerConnection c : m_IServerConnection)
			c.OnClientConnected(client, id);
		
		
		
		try {
			m_UDPConnection[id] = new UDPConnection(client.getInetAddress().toString(), basePort + id + 1, id);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
		
		
	}

	@Override
	public void OnClientDisconnected(Socket client, int id) {
		numClientsConnected--;
		
		for(IServerConnection c : m_IServerConnection)
			c.OnClientDisconnected(client, id);
		 
		m_TCPConnection[id] = null; 
		
		m_UDPConnection[id].close();
		m_UDPConnection[id].interrupt();
		m_UDPConnection[id] = null;
		
		
		
		
	}

}