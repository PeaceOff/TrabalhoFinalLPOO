package projeto.network;

import java.net.Socket;

/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:20
 */
public interface IClientConnection {

	/**
	 * 
	 * @param server
	 */
	public void ConnectedToServer(Socket server);

	/**
	 * 
	 * @param server
	 */
	public void DisconnectedFromServer(Socket server);

	public void ServerFull(Socket server);
	
}