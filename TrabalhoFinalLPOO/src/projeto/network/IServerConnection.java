package projeto.network;

import java.net.Socket;

/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:26
 */
public interface IServerConnection {

	/**
	 * 
	 * @param client
	 */
	public void OnClientAttemptConnecting(Socket client);

	/**
	 * 
	 * @param client
	 * @param id
	 */
	public void OnClientConnected(Socket client, int id);

	/**
	 * 
	 * @param client
	 * @param id
	 */
	public void OnClientDisconnected(Socket client, int id);

}