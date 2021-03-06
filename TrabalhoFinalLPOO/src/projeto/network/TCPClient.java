package projeto.network;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
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
	private boolean running = true;
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
		socket = new Socket();
		
	}
	
	public TCPClient(String ip, int port, IClientConnection listener) throws UnknownHostException, IOException{
		this.ip = ip;
		this.port = port;
		m_IClientConnection.add(listener);
		socket = new Socket();
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
		m_IClientConnection.clear();
		m_IClientConnection.add(listener); 
	}
	
	public void connect() throws IOException{
		socket.connect(new InetSocketAddress(ip,port), 10000);
		rebindConnection(ip);
	}
	
	public void close(){

		running = false;
		if(socket == null) return;
		try {
			socket.setSoTimeout(1);
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
	private void rebindConnection(String ip) throws IOException{
		DataInputStream in = new DataInputStream(socket.getInputStream());
		int newPort = in.readInt();
		if(newPort == 0){
			
			for(IClientConnection c : m_IClientConnection)
				c.ServerFull(socket);

			close();
			running=false;
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

	public boolean isConnected(){
		return running;
	}

	public int getPort(){

		if(socket == null){
			throw new IllegalAccessError();
		}

		return socket.getPort();
	}

	@Override
	public void run() {

		while(running){
			
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

				close();

			}
			
		}
	}

}