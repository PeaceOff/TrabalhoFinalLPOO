package projeto.network;

import java.io.IOException;

/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:27
 */
public abstract class TCPBasic {

	protected IMessage m_IMessage = null;
	
	public TCPBasic(){

	}

	/**
	 * 
	 * @param listener
	 */
	public void setMessageListener(IMessage listener){
		m_IMessage = listener;
	} 

	/**
	 * 
	 * @param msg
	 */
	public abstract void sendInfo(byte[] msg) throws IOException;

}