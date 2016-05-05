package projeto.network;


/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:24
 */
public interface IMessage {

	/**
	 * 
	 * @param info
	 * @param id
	 */
	public void OnMessageReceived(byte[] info, int id);
	
	public void ClearBuffer(int index);
}