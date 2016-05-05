package projeto.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


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
	private AtomicInteger numClientsConnected = new AtomicInteger();
	private IMessage messageParser = null;

	public Host(int port, int numClients) throws IOException{
		basePort = port;
		this.numClients = numClients;
		
		m_TCPConnection = new TCPServerClient[numClients];
		m_UDPConnection = new UDPConnection[numClients];
		svSocket = new ServerSocket(basePort); 
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
		return numClientsConnected.get(); 
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
	
		
		
		while(numClientsConnected.get() < numClients){
			 System.out.println("C:" + numClientsConnected +"/" + numClients);
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
				
				try { 
					synchronized (this) { 
						this.wait();
					}
					
					
					svSocket = new ServerSocket(basePort);
					
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					return;
				} catch (IOException e1) {
					return;
				}
			}
		}
		
		System.out.println("Thread Server Acabou!");
	}

	@Override
	public void OnClientAttemptConnecting(Socket client) {
		for(IServerConnection c : m_IServerConnection)
			c.OnClientAttemptConnecting(client);
		
		
	}

	@Override
	public void OnClientConnected(Socket client, int id) {
		System.out.print("CONNECTEDDDDDDDD" + numClientsConnected);
		
		numClientsConnected.incrementAndGet();
		
		if(numClients == numClientsConnected.get()){ 
			synchronized(Host.class){
				try { 
					svSocket.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 	
			
			}
		}
		
		
		
		for(IServerConnection c : m_IServerConnection)
			c.OnClientConnected(client, id);
		
		
		  
		try {
			m_UDPConnection[id] = new UDPConnection(client.getInetAddress().getHostAddress(), basePort + id + 1, 0 , id + numClients);
			m_UDPConnection[id].setMessageParser(messageParser); 
			m_UDPConnection[id].start();  
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
		
		
	}

	@Override
	public void OnClientDisconnected(Socket client, int id) {
		numClientsConnected.decrementAndGet();

		System.out.println("Disconnected: " + numClientsConnected.get() + numClients);
		if(numClients - 1 == numClientsConnected.get()){

			System.out.println("Ready To Receive!");
			synchronized (this) {
				this.notify(); 
			}
			
		}
		
		
		
		for(IServerConnection c : m_IServerConnection)
			c.OnClientDisconnected(client, id);
		 
		m_TCPConnection[id] = null; 
		
		m_UDPConnection[id].close();
		m_UDPConnection[id].interrupt(); 
		m_UDPConnection[id] = null;
		
		
		
		
	}

}