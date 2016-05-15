package projeto.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:28
 */
public class TCPServerClient extends TCPBasic implements Runnable {

	private Socket client = null;
	private final int id;
	private ServerSocket svSocket = null; 
	private ArrayList<IServerConnection> m_IServerConnection = new ArrayList<IServerConnection>();

	public void finalize() throws Throwable {
		super.finalize();
	} 

	/**
	 * 
	 * @param port
	 * @throws IOException 
	 */
	public TCPServerClient(int port, int id) throws IOException{
		svSocket= new ServerSocket(port); 
		this.id = id;
	}

	/**
	 * 
	 * @param listener
	 */
	public void addConnectionListener(IServerConnection listener){
		m_IServerConnection.add(listener);
	}

	/**
	 * 
	 * @param msg
	 * @throws IOException  
	 */
	public void sendInfo(byte[] msg){
		try {
			client.getOutputStream().write(msg);
		} catch (IOException e) {
			for(IServerConnection c : m_IServerConnection){
				c.OnClientDisconnected(client, id);
				try {
					close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {
			client = svSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
			return;

		}

		for(IServerConnection c : m_IServerConnection)
			c.OnClientConnected(client, id); 


		while(client!=null){	

			try {

				byte[] info = new byte[255];
				int readed;

				readed = client.getInputStream().read(info);
				if(readed == -1) {
					for(IServerConnection c : m_IServerConnection)
						c.OnClientDisconnected(client, id);
					return;
				}
				byte[] msg = new byte[readed];
				for(int i =0 ; i < readed; i++){
					msg[i] = info[i];
				} 


				m_IMessage.OnMessageReceived(msg, id);
 

			} catch (IOException e) { 

				for(IServerConnection c : m_IServerConnection)
					c.OnClientDisconnected(client, id);

				try {
					close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  


				return;
			}
		}
	}

	public void close()throws IOException{
		if(svSocket != null)
			svSocket.close();
		svSocket = null;

		if(client != null)
			client.close(); 
		client = null;
	}

}