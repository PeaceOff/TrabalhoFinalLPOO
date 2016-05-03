package projeto.network;


/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:23
 */
public interface ICommandReceived {

	/**
	 * 
	 * @param cmd
	 * @param index
	 */
	public void CommandReceived(byte[] cmd, int index);

}